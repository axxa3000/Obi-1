package com.dal.judobeltsf.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.model.beltSchedule2
import com.example.judobelts.Databases.JudokaDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period


class FirstFragmentViewModel(private val judokaDao: JudokaDao): ViewModel() {

    fun allJudokas(): Flow<List<JudokaEntity>> = judokaDao.getAllJudoka()

    fun createJudoka( lic :String, name :String, lastName: String, dob: LocalDate,  dsj:LocalDate ){
        val judoPlayer = JudokaEntity(lic,
            name,
            lastName,
            dob,
            dsj,
            judokaBeltMaker2(dob,dsj)
        )
        insert(judoPlayer)
    }
    fun insert(judoka: JudokaEntity) = viewModelScope.launch {
        judokaDao.addJudoka(judoka)
    }

   fun oneJudokaByLic(lic: String): Flow<JudokaEntity> = judokaDao.getJudokaByLicense(lic)

   fun delete(j: JudokaEntity) = viewModelScope.launch {
       judokaDao.delete(j)
   }


}
private fun judokaBeltMaker2 (dob:LocalDate,judoStartDate:LocalDate) :String {
    var upgradeDates: List<String>
    val ageStartudo = Period.between( dob, judoStartDate ).years
    //check latency period
    if( Period.between( dob, judoStartDate).years >= 15 ){
        upgradeDates = beltArrBuilder2(beltSchedule2[11], judoStartDate )
        return upgradeDates.toString()
    }
            if( Period.between( dob, judoStartDate.plusMonths(
            beltSchedule2[ageStartudo-4][0].toLong() )).years < ageStartudo+1){
        upgradeDates = beltArrBuilder2(beltSchedule2[ageStartudo-4], judoStartDate )

        Log.d("","Period: "+ Period.between( dob, judoStartDate.plusMonths(
            beltSchedule2[ageStartudo-4][0].toLong() )).years+ " < "+ (ageStartudo+1) )
        Log.d("","Table age: "+ ageStartudo)
    }else{
        upgradeDates = beltArrBuilder2(beltSchedule2[ageStartudo-3], judoStartDate )
        Log.d("","Table age: "+ (ageStartudo+1))

    }
    return upgradeDates.toString()
}


fun beltArrBuilder2(months: IntArray, judoStartDate: LocalDate): MutableList<String> {
    val arr = mutableListOf<String>()
    var c= 0
    var a=0
    for(i in months) {
        c += i
        if(i!=0) {
            // arr.add(judoStartDate.plus(Period.of(0, c, 0)))
            arr.add( judoStartDate.plus(Period.of(0, c, 0)).toString()+",")
        }
        a++
    }
    return arr
}

class FirstFragmentViewModelFactory(
    private val judokaDao: JudokaDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstFragmentViewModel(judokaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}