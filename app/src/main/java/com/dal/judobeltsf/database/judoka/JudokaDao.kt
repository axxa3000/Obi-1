package com.example.judobelts.Databases

import androidx.room.*
import com.dal.judobeltsf.database.judoka.JudokaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JudokaDao {
    @Query("SELECT * FROM judokas_list")
    fun getAllJudoka(): Flow<List<JudokaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJudoka(judoka : JudokaEntity)

    @Query("SELECT * FROM judokas_list where license  = :lic")
//  @Query("SELECT * FROM judoka_entity where id like:arg0")
    fun getJudokaByLicense(lic: String): Flow<JudokaEntity>

    @Delete
    suspend fun delete( j: JudokaEntity)
}
/**
 * Provides access to read/write operations on the schedule table.
 * Used by the view models to format the query results for use in the UI.

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAll(): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): Flow<List<Schedule>>

}
 */