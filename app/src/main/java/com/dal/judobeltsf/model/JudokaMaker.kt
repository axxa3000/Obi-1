package com.example.judobelts.model

import com.dal.judobeltsf.model.BeltSchedule
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.Period

open class JudokaMaker (val name: String, val lastName: String, val birthDate: String, val judoStartingDate: String) {
    val age: Int
    val judoDatesToUpgradebelt: MutableList<LocalDate>

    init {
        age = getAge( LocalDate.of(2013, 1, 1) )//7
        judoDatesToUpgradebelt =  when (age) {
            7 ->  beltBuilder(BeltSchedule.AGE_SIX.months, LocalDate.of(2020, 1, 1) )
            9 ->  beltBuilder(BeltSchedule.AGE_SIX.months, LocalDate.of(2020, 1, 1) )

            else -> throw IllegalArgumentException("Can't manage age")
        }
        //  judoDatesToUpgradebelt = beltBuilder(BeltSchedule.AGEFOUR.months, LocalDate.of(2020, 1, 1))

    }

    fun beltBuilder(months: IntArray, beginDate: LocalDate): MutableList<LocalDate> {
        val arr = mutableListOf<LocalDate>()
        for(i in months) {
            arr.add(beginDate.plus(Period.of(0, i, 0)))
        }
        return arr
    }

    fun getAge(dob: LocalDate): Int {
        //val date =  LocalDate.of(2015, 4, 8)

        return Period.between(
            dob,
            LocalDate.now()//today 2022-11-25
        ).years
    }
}
