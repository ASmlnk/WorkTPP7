package com.asmlnk.android.asmlnk.worktpp_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class EquipmentInOperationListFragment: Fragment() {

    private lateinit var viewPagerBoilerUnit: ViewPager2
    private lateinit var viewPagerTurbogenerator: ViewPager2
    private lateinit var tabLayoutTurbogenerator: TabLayout
    private lateinit var tabLayoutBoilerUnit: TabLayout
    private var adapterBoiler: AdapterEquipmentCategory? = null
    private var adapterTurbogenerator: AdapterEquipmentCategory? = null

    private val equipmentInOperationViewModel: EquipmentInOperationViewModel by lazy {
        ViewModelProvider (this) [EquipmentInOperationViewModel :: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.equipment_work, container, false)

        viewPagerBoilerUnit = view.findViewById(R.id.pager_boiler_unit) as ViewPager2
        tabLayoutBoilerUnit = view.findViewById(R.id.tab_layout_boiler_unit) as TabLayout
        viewPagerTurbogenerator = view.findViewById(R.id.pager_turbogenerator) as ViewPager2
        tabLayoutTurbogenerator = view.findViewById(R.id.tab_layout_turbogenerator) as TabLayout



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listBoilerUnit = equipmentInOperationViewModel.listBoilerUnit
        val listTurbogenerator = equipmentInOperationViewModel.listTurbogenerator

        adapterBoiler = AdapterEquipmentCategory(listBoilerUnit)
        viewPagerBoilerUnit.adapter = adapterBoiler
        TabLayoutMediator(tabLayoutBoilerUnit, viewPagerBoilerUnit) { tab, position ->
            tab.text = listBoilerUnit[position].name
        }.attach()

        adapterTurbogenerator = AdapterEquipmentCategory(listTurbogenerator)
        viewPagerTurbogenerator.adapter = adapterTurbogenerator
        TabLayoutMediator(tabLayoutTurbogenerator, viewPagerTurbogenerator) { tab, position ->
            tab.text = listTurbogenerator[position].name
        }.attach()


    }

    companion object {
        fun newInstance(): EquipmentInOperationListFragment {
            return EquipmentInOperationListFragment()
        }
    }


}