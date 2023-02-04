package com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asmlnk.android.asmlnk.worktpp_7.R

class AdapterInspectionSchedulePager(fragments: List<Fragment>,
                                     fragmentManager: FragmentManager,
                                     lifecycle: Lifecycle,
                                     workingShift: String):
    FragmentStateAdapter(fragmentManager, lifecycle) {


    private val _fragments: List<Fragment> = fragments
    private val _workingShift = workingShift

    override fun getItemCount(): Int = _fragments.size

    override fun createFragment(position: Int): Fragment {
        val fragment = _fragments[position]
        fragment.arguments = Bundle().apply {
            putSerializable(ARG_OBJECT, _workingShift)
        }
        return fragment
    }
}