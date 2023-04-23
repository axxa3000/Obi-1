package com.dal.judobeltsf.database.judoka

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

//@Parcelize
@Entity(tableName = "judokas_list")
data class JudokaEntity(
  //  @PrimaryKey(autoGenerate = true) val id: Int,
    @PrimaryKey @NonNull @ColumnInfo(name = "license") val license: String,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "last_name") val lastName: String,
    @NonNull @ColumnInfo(name = "dob") val dob: LocalDate,
    @NonNull @ColumnInfo(name = "judo_start_date") val judoStartDate: LocalDate,
    @NonNull @ColumnInfo(name = "upgrade_belt_dates") val upgradeBeltDates: String
    )
/*
*
/**
 * Represents a single table in the database. Each row is a separate instance of the Schedule class.
 * Each property corresponds to a column. Additionally, an ID is needed as a unique identifier for
 * each row in the database.
 */
@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)*/