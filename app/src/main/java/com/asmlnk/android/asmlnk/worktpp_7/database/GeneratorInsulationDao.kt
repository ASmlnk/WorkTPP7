package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.GeneratorInsulation

@Dao
interface GeneratorInsulationDao {

    @Query("SELECT * FROM generatorInsulation")

    fun getGeneratorInsulation(): LiveData<List<GeneratorInsulation>>

    @Insert
    fun addGeneratorInsulation (generatorInsulation: GeneratorInsulation)

    @Delete
    fun deleteGeneratorInsulation (generatorInsulation: GeneratorInsulation)

}