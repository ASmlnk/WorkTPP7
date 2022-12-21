package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DefectListFragment: Fragment() {

    interface Callbacks {
        fun onDefectSelected(defectId: UUID)
    }

    private var callbacks: Callbacks? = null
    private lateinit var defectRecyclerView: RecyclerView
    private var adapter: DefectAdapter = DefectAdapter(emptyList())
    private val defectListViewModel : DefectListViewModel by lazy {
        ViewModelProvider (this) [DefectListViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.defect_list_fragment, container, false)

        defectRecyclerView = view.findViewById(R.id.defect_recycler_view) as RecyclerView
        defectRecyclerView.layoutManager = LinearLayoutManager(context)
        defectRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defectListViewModel.defectListLiveData.observe(viewLifecycleOwner, Observer { defects ->
            defects?.let {
                updateUI(defects)
            }  //Наблюдаем за листом дефектов из БД, как только он появиться в DefectListViewModel, наблюдатель его обнаружит и добавит сюда
        })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_defect_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_defect -> {
                val defect = Defect()
                defectListViewModel.addDefect(defect)
                callbacks?.onDefectSelected(defect.id)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(defects: List<Defect>) {
        adapter = DefectAdapter(defects)
        defectRecyclerView.adapter = adapter
    }


    companion object {
        fun newInstance(): DefectListFragment {
            return DefectListFragment()
        }
    }

    private inner class DefectHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

        private lateinit var defect: Defect
        private val titleTextView: TextView = itemView.findViewById(R.id.defect_title)
        private val defectView: LinearLayout = itemView.findViewById(R.id.defect_view)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bind(defect: Defect) {
            this.defect = defect
            titleTextView.text = this.defect.title
            if (this.defect.logging) {
                defectView.apply {
                    defectView.setBackgroundResource(R.color.red_defect)
                }
            }
            if (this.defect.defectFixed) {
                defectView.setBackgroundResource(R.color.green_defect)
            }
        }

        override fun onClick(v: View?) {
            callbacks?.onDefectSelected(defect.id)
        }

        override fun onLongClick(v: View?): Boolean {

                defectListViewModel.deleteDefect(defect)

            return true
        }

    }

    private inner class DefectAdapter(var lists: List<Defect>): RecyclerView.Adapter<DefectHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefectHolder {
            val view = layoutInflater.inflate(R.layout.list_item_defect, parent, false )
            return DefectHolder(view)
        }

        override fun onBindViewHolder(holder: DefectHolder, position: Int) {
            val defect = lists[position]
            holder.bind(defect)
        }

        override fun getItemCount() = lists.size

       /* fun setList(newList: List<Defect>) {
            lists.clear()
            lists.addAll(newList)
            notifyDataSetChanged()
        }*/



    }

}