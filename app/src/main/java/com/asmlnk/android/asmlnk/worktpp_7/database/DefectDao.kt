package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.room.Dao
import androidx.room.Query
import com.asmlnk.android.asmlnk.worktpp_7.Defect
import java.util.*

@Dao
interface DefectDao {
    @Query("SELECT * FROM defect")
    fun getDefects(): List<Defect>

    @Query("SELECT * FROM defect WHERE id=( :id)")
    fun  getDefect(id: UUID): Defect?
}