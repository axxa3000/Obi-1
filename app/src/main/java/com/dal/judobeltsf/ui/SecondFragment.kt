package com.dal.judobeltsf.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dal.judobeltsf.BeltApp
import com.dal.judobeltsf.R
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.databinding.FragmentSecondBinding
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModel
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var judoka : JudokaEntity
    private var _binding: FragmentSecondBinding? = null
    private val args: SecondFragmentArgs by navArgs()

    private val viewModel: FirstFragmentViewModel by activityViewModels {
        FirstFragmentViewModelFactory(
            (activity?.application as BeltApp).database.judokaDao()
        )
    }



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   Log.e("second frag", "Fragment receive information: "+ args.lic )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = binding.textviewSecond
        val del = binding.buttonDel
//TODO  requireActivity().applicationContext show
// java.lang.IllegalStateException: You need to use a Theme.AppCompat theme
// (or descendant) with this activity. then use requireContext()
         val builder = AlertDialog.Builder( requireContext()  )
            .setMessage("¿Confirma que desea borrar los datos?")
            .setCancelable(false)
            .setNegativeButton("No") { dialog, _ ->
                //Toast.makeText(requireContext(), "Ok button pressed", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setPositiveButton("Si") { dialog, _ ->
                //Toast.makeText(requireContext(), "Datos borrados", Toast.LENGTH_SHORT).show()
                viewModel.delete(judoka)
                del.visibility = View.INVISIBLE
                dialog.dismiss()
            }

        lifecycle.coroutineScope.launch {
            viewModel.oneJudokaByLic( args.lic ).collect() {
               // Log.e("", it.dob.toString())
                judoka = it
                if(it == null){
                    result.setText( "Borrado" )
                }else{
                    result.setText( it.toString() )
                    // result.setText( judokaBeltMaker(it).distinct().toString() )
                }
            }
        }
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.buttonDel.setOnClickListener {
            val alert = builder.create()
            alert.show()
        }

    }

/*
    private fun judokaBeltMaker (j: JudokaEntity) :List<String>{

        var upgradeDates: List<String>
        val ageWhenStartudo = Period.between( j.dob,j.judoStartDate ).years

        /*Check age and period of grace in  BeltSchedule.AGE_FIFTEEN.months[1] is 4 months
        dob:2000-9-2
        dsj:2015-6-2
        it's not 15`old but will be in 3 months so can use table of 15
         */
        upgradeDates =  when (ageWhenStartudo) {// 0..3
           0,1,2,3 -> throw IllegalArgumentException("Can't manage less than age 3")
            4 ->  beltBuilder(BeltSchedule.AGE_FOUR.months, j.judoStartDate )
            5 ->  beltBuilder(BeltSchedule.AGE_FIVE.months, j.judoStartDate )
            6 ->  beltBuilder(BeltSchedule.AGE_SIX.months, j.judoStartDate )
            7 ->  beltBuilder(BeltSchedule.AGE_SEVEN.months, j.judoStartDate )
            8 ->  beltBuilder(BeltSchedule.AGE_EIGHT.months, j.judoStartDate )
            9 ->  beltBuilder(BeltSchedule.AGE_NINE.months, j.judoStartDate )
            10 ->  beltBuilder(BeltSchedule.AGE_TEN.months, j.judoStartDate )
            11 ->  if( Period.between( j.dob, j.judoStartDate.plusMonths(
                    BeltSchedule.AGE_TWELVE.months[0].toLong() )).years < 12){
                Log.d("", "Period: "+Period.between( j.dob, j.judoStartDate.plusMonths(
                    BeltSchedule.AGE_TWELVE.months[0].toLong() )).years )
                Log.d("","AGE_ELEVEN")
                return  beltBuilder(BeltSchedule.AGE_ELEVEN.months, j.judoStartDate)
            }else{
                Log.d("","AGE_TWELVE")
                return  beltBuilder(BeltSchedule.AGE_TWELVE.months, j.judoStartDate)
            }
            12 ->  beltBuilder(BeltSchedule.AGE_TWELVE.months, j.judoStartDate )
            13 ->  beltBuilder(BeltSchedule.AGE_THIRTEEN.months, j.judoStartDate )
            14 -> if( Period.between( j.dob, j.judoStartDate.plusMonths(
                    BeltSchedule.AGE_FIFTEEN.months[1].toLong() )).years < 15){
                return  beltBuilder(BeltSchedule.AGE_FOURTEEN.months, j.judoStartDate)
            }else{
                return  beltBuilder(BeltSchedule.AGE_FIFTEEN.months, j.judoStartDate)
            }
            else  ->  beltBuilder(BeltSchedule.AGE_FIFTEEN.months, j.judoStartDate )

        }
        return upgradeDates
  }

    fun beltBuilder(months: IntArray, judoStartDate: LocalDate): MutableList<String> {
        val arr = mutableListOf<String>()
        var c= 0
        var a=0
        for(i in months) {
           c=c+i
            if(i!=0) {
               // arr.add(judoStartDate.plus(Period.of(0, c, 0)))
                arr.add("Cinturon: " + beltColor[a] +
                            " a partir de: " + judoStartDate.plus(Period.of(0, c, 0)).toString()
                )
            }
            a++
        }
        return arr
    }
*/
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}