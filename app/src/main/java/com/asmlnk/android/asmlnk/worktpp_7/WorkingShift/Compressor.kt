package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Compressor (@PrimaryKey val id: UUID = UUID.randomUUID(),
                       val date: Date = Date(),
                       var vk1: String = "",
                       var vk2: String = "",
                       var vk4: String = "",
                       var vk5: String = "",
                       var vk6: String = "") {
}