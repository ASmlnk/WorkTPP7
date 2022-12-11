package com.asmlnk.android.asmlnk.worktpp_7

import java.util.*

data class Defect (
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var details: String = "",
    var logging: Boolean = false,
    var defectFixed: Boolean = false,
    var date: Date = Date()
    ) {
}