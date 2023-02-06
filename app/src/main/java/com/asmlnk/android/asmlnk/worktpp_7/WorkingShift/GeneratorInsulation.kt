package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GeneratorInsulation (@PrimaryKey val id: UUID = UUID.randomUUID(),
                                val date: Date = Date(),
                                var name: String = "",
                                var stator: String = "",
                                var rotor: String = "",
                                var pathogen: String = "") {
}