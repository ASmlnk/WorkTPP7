package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.Compressor

@Dao
interface CompressorDAO {

    @Query("SELECT * FROM compressor")
    fun getCompressors(): LiveData<List<Compressor>>

    @Insert
    fun addCompressor(compressor: Compressor)

    @Delete
    fun deleteCompressor(compressor: Compressor)

}