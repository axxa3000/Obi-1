package com.dal.judobeltsf

import android.icu.lang.UCharacter.getAge
import android.util.Log
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.model.BeltSchedule
import com.dal.judobeltsf.model.Judoka
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.Period

interface Logger{

    val tag: String
        get() = javaClass.simpleName

    fun LogD(msg: String){
        Log.d(tag, msg)
    }
}
