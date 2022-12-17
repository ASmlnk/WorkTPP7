package com.asmlnk.android.asmlnk.worktpp_7

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Defect (
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var details: String = "",
    var logging: Boolean = false,
    var defectFixed: Boolean = false
    //var date: Date = Date()
    ) {
    val photoFileName
    get() = "IMG_$id.jpg"
}