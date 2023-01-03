package com.asmlnk.android.asmlnk.worktpp_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EquipmentInOperationListFragment: Fragment() {


   // private lateinit var equipmentRecyclerView: RecyclerView
   // private var adapter: AdapterEquipmentCategory? = null

    private val equipmentInOperationViewModel: EquipmentInOperationViewModel by lazy {
        ViewModelProvider (this) [EquipmentInOperationViewModel :: class.java]
    }
    private val listAdapter = listOf("К/А", "ТГ", "СН", "СМ", "ПН", "ЦН", "БНТ", "КМ", "ДрН ДБ")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.equipment_work, container, false)

        /*equipmentRecyclerView = view.findViewById(R.id.defect_recycler_view) as RecyclerView
        equipmentRecyclerView.layoutManager = LinearLayoutManager(context)
        equipmentRecyclerView.adapter = adapter*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*updateUI(listAdapter)*/
    }

    /*private fun updateUI(list: List<String>) {
        adapter = AdapterEquipmentCategory(list)
        equipmentRecyclerView.adapter = adapter
    }*/

    companion object {
        fun newInstance(): EquipmentInOperationListFragment {
            return EquipmentInOperationListFragment()
        }
    }

    private inner class  EquipmentCategory(val name: String, val listElectricMotor:  List<ElectricMotor>) {

       private fun collectAll (listElectricMotor: List<ElectricMotor>) {
           for (item in listElectricMotor) {
               item.schemaState = true
           }
       }

        private fun takeApartAll (listElectricMotor: List<ElectricMotor>) {
            for (item in listElectricMotor) {
                item.schemaState = false
            }
        }

    }

}