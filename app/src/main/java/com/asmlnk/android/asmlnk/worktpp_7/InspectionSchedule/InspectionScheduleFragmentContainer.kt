package com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.asmlnk.android.asmlnk.worktpp_7.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*

class InspectionScheduleFragmentContainer: Fragment() {

    private lateinit var textData: TextView
    private lateinit var tabLayoutExecutor: TabLayout
    private lateinit var pagerInspectionSchedule: ViewPager2
    private lateinit var adapter: AdapterInspectionSchedulePager

    private val inspectionScheduleViewModel: InspectionScheduleViewModel by lazy {
        ViewModelProvider(this) [InspectionScheduleViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.inspection_schedule_fragment, container, false)

        textData = view.findViewById(R.id.data_text) as TextView
        tabLayoutExecutor = view.findViewById(R.id.tab_layout_executor) as TabLayout
        pagerInspectionSchedule = view.findViewById(R.id.pager_inspection_schedule) as ViewPager2

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(InspectionScheduleFragmentN(), InspectionScheduleFragmentD())
        val workingShift: String = arguments?.getSerializable(InSc.WORKING_SHIFT.get) as String

        adapter = AdapterInspectionSchedulePager(fragments, childFragmentManager, lifecycle, workingShift)
        pagerInspectionSchedule.adapter = adapter

        TabLayoutMediator(tabLayoutExecutor, pagerInspectionSchedule) { tab, position ->
            when (position) {
                0 -> tab.text = InspectionScheduleFragmentN().nameFragment
                1 -> tab.text = InspectionScheduleFragmentD().nameFragment
            }
        }.attach()

        val calendar = inspectionScheduleViewModel.getCalendar(workingShift)

        val data = calendar.timeInMillis
        val pattern = "EEE, d MMM yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val dateFormat = simpleDateFormat.format(data)
        val shift = if (workingShift == InSc.DAY.get) "с 8.00 до 20.00" else "с 20.00 до 8.00"
        textData.text = "$dateFormat     смена $shift"

    }

    companion object {
        fun newInstance(workingShift: String): InspectionScheduleFragmentContainer  {
            val args = Bundle().apply {
                putSerializable(InSc.WORKING_SHIFT.get, workingShift )
            }
            return InspectionScheduleFragmentContainer().apply {
                arguments = args
            }
        }
    }
}