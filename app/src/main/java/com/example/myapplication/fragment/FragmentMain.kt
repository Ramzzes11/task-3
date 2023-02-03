package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.constant.MAIN
import com.example.myapplication.databinding.FragmentMainBinding

class FragmentMain : Fragment() {

    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val args: FragmentMainArgs by navArgs()

        binding.nameSurname.text = args.nameSurname

        binding.viewMyCont.setOnClickListener{
            findNavController().navigate(
                R.id.action_fragmentMain_to_myContactsFragment
            )
        }
    }



//    companion object{
//
//        const val BOX = "BOX"
//
//        private const val NAME_SURNAME_USER = "NAME_SURNAME_USER"
//
//        fun newInstance(nameSurname: String): FragmentMain {
//            val args = Bundle().apply {
//                putString(NAME_SURNAME_USER,nameSurname)
//            }
//            val fragment = FragmentMain()
//            fragment.arguments = args
//            return fragment
//        }
//
//    }

}