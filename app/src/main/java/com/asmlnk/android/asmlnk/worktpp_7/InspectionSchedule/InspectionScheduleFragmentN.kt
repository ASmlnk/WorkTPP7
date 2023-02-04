package com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

const val ARG_OBJECT = "object"

class InspectionScheduleFragmentN: Fragment() {

    val nameFragment = InSc.NSE.get

    private lateinit var inspectionScheduleRecyclerView: RecyclerView
    private val adapterList: MutableList<Inspection> = mutableListOf()
    private var adapter: AdapterInspectionSchedule = AdapterInspectionSchedule(adapterList)

    private val inspectionScheduleViewModel: InspectionScheduleViewModel by lazy {
        ViewModelProvider(this) [InspectionScheduleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.inspection_schedule_list_fragment, container, false)

        inspectionScheduleRecyclerView = view.findViewById(R.id.inspection_schedule_recycler_view) as RecyclerView
        inspectionScheduleRecyclerView.layoutManager = LinearLayoutManager(context)
        inspectionScheduleRecyclerView.adapter = adapter

     return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inspectionScheduleViewModel.inspectionScheduleLiveDataN.observe(viewLifecycleOwner) {
            updateUI(it)
        }

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            val workingShift: String = getSerializable ( ARG_OBJECT ).toString()
            inspectionScheduleViewModel.getInspectorSchedule ( workingShift, InSc.NSE.get )
        }
    }

    private fun updateUI(list: List<Inspection>) {
        adapter.list.addAll(list)
        adapter.notifyDataSetChanged()
    }

}