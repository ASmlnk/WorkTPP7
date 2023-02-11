package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.room.ColumnInfo
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
                       var vk6: String = "",
                       @ColumnInfo(name = "compressors1", defaultValue = "0")
                       var compressors1: String = "0",
                       @ColumnInfo(name = "compressors2", defaultValue = "0")
                       var compressors2: String= "0") {
}