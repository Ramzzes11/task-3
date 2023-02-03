package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.constant.MAIN
import com.example.myapplication.databinding.FragmentMain1Binding
import com.example.myapplication.databinding.FragmentMainBinding

class FragmentMain1 : Fragment() {

    lateinit var binding: FragmentMain1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMain1Binding.inflate(inflater,container,false)
        binding.nameSurname.text = arguments?.getString(NAME_SURNAME_USER)
        binding.viewMyCont.setOnClickListener { launchNext() }
        return binding.root
    }

    private fun launchNext() {
        val myContactFragment = MyContactsFragment1()
        parentFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right,
                R.anim.slide_out_right,R.anim.slide_out_left)
            .addToBackStack(null)
            .replace(R.id.container1,myContactFragment)
            .commit()
    }

   companion object{

        private const val NAME_SURNAME_USER = "NAME_SURNAME_USER"

        fun newInstance(nameSurname: String):FragmentMain1{
            val args = Bundle().apply {
                putString(NAME_SURNAME_USER,nameSurname)
            }
            val fragment = FragmentMain1()
            fragment.arguments = args
            return fragment
        }
    }

}