package com.asmlnk.android.asmlnk.worktpp_7

import android.app.Application

class WorkTPP7Application: Application() {
    override fun onCreate() {
        super.onCreate()
        DefectRepository.initialize(this)
    }
}