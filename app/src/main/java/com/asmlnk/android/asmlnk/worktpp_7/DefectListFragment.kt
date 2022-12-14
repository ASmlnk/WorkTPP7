package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
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

    private inner class DefectHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var defect: Defect
        private val titleTextView: TextView = itemView.findViewById(R.id.defect_title)
        private val defectView: LinearLayout = itemView.findViewById(R.id.defect_view)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(defect: Defect) {
            this.defect = defect
            titleTextView.text = this.defect.title
            if (this.defect.logging) {
                defectView.setBackgroundResource(R.color.red_defect)
            }
            if (this.defect.defectFixed) {
                defectView.setBackgroundResource(R.color.green_defect)
            }
        }

        override fun onClick(v: View?) {
            callbacks?.onDefectSelected(defect.id)
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