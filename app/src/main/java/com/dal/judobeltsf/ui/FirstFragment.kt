package com.dal.judobeltsf.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dal.judobeltsf.BeltApp
import com.dal.judobeltsf.R
import com.dal.judobeltsf.database.judoka.JudokaEntity
import com.dal.judobeltsf.databinding.FragmentFirstBinding
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModel
import com.dal.judobeltsf.ui.viewmodels.FirstFragmentViewModelFactory
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var recyclerView: RecyclerView

    private val viewModel: FirstFragmentViewModel by activityViewModels {
        FirstFragmentViewModelFactory(
            (activity?.application as BeltApp).database.judokaDao()
        )
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fab.setOnClickListener {

            findNavController().navigate(R.id.action_FirstFragment_to_CreateJudokaFragment)

        }
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val Adapter = Adapter {
            val action = FirstFragmentDirections
                .actionFirstFragmentToSecondFragment(
                    lic = it.license
                )
            view.findNavController().navigate(action)
        }
/*
        recyclerView.adapter = Adapter
        Adapter.submitList( getJudokas() )
*/
        recyclerView.adapter = Adapter
        lifecycle.coroutineScope.launch {
            viewModel.allJudokas().collect() {
                Adapter.submitList(it)
            }
        }


    }
/*
    fun getJudokas(): MutableList<JudokaEntity>{
        var judokas:MutableList<JudokaEntity> = ArrayList()
        judokas.add(JudokaEntity(1, "Marvel", "1-1-015" ))
        judokas.add(JudokaEntity(2, "2Marvel", "1-1-015"))
        judokas.add(JudokaEntity(3, "3Marvel", "1-1-015"))

        return judokas
    }

 */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}