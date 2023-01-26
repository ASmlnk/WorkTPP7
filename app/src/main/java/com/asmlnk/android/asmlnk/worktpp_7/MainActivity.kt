package com.asmlnk.android.asmlnk.worktpp_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectFragment
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectListFragment
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.EquipmentInOperationListFragment
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.WorkingShiftCompressorFragment
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.WorkingShiftControlMeasurementFragment
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.WorkingShiftFragment
import java.util.*

class MainActivity :
    AppCompatActivity(),
    DefectListFragment.Callbacks,
    WorkTPP7Menu.Callbacks,
    WorkingShiftFragment.Callbacks{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = WorkTPP7Menu.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onDefectSelected(defectId: UUID) {
        val fragment = DefectFragment.newInstance(defectId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDefectListSelected() {
        val fragment = DefectListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onEquipmentListSelected() {
        val fragment = EquipmentInOperationListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onWorkingShift() {
        val fragment = WorkingShiftFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCompressorSelected() {
        val fragment = WorkingShiftCompressorFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onControlMeasurementSelected() {
        val fragment = WorkingShiftControlMeasurementFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}