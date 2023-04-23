package com.dal.judobeltsf.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dal.judobeltsf.BeltApp
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.databinding.FragmentCreateJudokaBinding
import com.dal.judobeltsf.model.BeltSchedule
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModel
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModelFactory
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.Period


class CreateJudokaFragment : Fragment() {

    private var _binding: FragmentCreateJudokaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstFragmentViewModel by activityViewModels {
       FirstFragmentViewModelFactory(
           (activity?.application as BeltApp).database.judokaDao()
       )
   }

    lateinit var et_dob : EditText
    lateinit var et_dateStartJudo : EditText
    /*
lateinit var button_enterJudoka : Button
lateinit var nameJudoka :String
lateinit var lastNameJudoka :String
lateinit var licenceJudoka :String

 */
    lateinit var dob1 : LocalDate
    lateinit var dateStartJudo1 : LocalDate

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreateJudokaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_dob =  binding.etDob
        et_dateStartJudo = binding.etDateStartJudo
        val button_enterJudoka = binding.bEnterJudoka
        val nameJudoka = binding.etName.text
        val lastNameJudoka = binding.etLastName.text
        val licenceJudoka = binding.etLicense.text


        binding.etDob.setOnClickListener {showDatePickerDialog(0)}
        binding.etDateStartJudo.setOnClickListener {showDatePickerDialog(1)}
        button_enterJudoka.setOnClickListener {
            //TODO: check parameters not null

            viewModel.createJudoka(
                licenceJudoka.toString(),
                nameJudoka.toString(),
                lastNameJudoka.toString(),
                dob1,
                dateStartJudo1
            )
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDatePickerDialog(num:Int) {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year, num) }
       datePicker.show( parentFragmentManager , "datePicker")

    }
    /*private fun showDatePickerDialog2(num:Int) {
        val newFragment =  DatePickerFragment{ day, month, year -> onDateSelected(day, month, year, num) }
        getActivity()?.let { newFragment.show(it.getSupportFragmentManager(), "datePicker") }
    }*/
    private fun onDateSelected(day: Int, month: Int, year: Int, num: Int){
        if(num<1) {
            et_dob.setText("$day / $month / $year")
           dob1 = LocalDate.of( year ,month, day)
        }else{
            et_dateStartJudo.setText("$day / $month / $year")
            dateStartJudo1 = LocalDate.of( year ,month, day)
        }
    }
}