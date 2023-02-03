package com.example.myapplication.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
//import com.example.myapplication.ContactProfileFragmentArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentContactProfileBinding
import com.example.myapplication.model.getInCircleGlide


class ContactFragmentProfile1 : Fragment() {

    lateinit var binding: FragmentContactProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactProfileBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments

        binding.nameSurname.text = args?.getString("NameSurname")
        binding.career.text = args?.getString("Career")
        val photo = args?.getString("Photo")
        getInCircleGlide(photo!!,binding.contactImage)
    }
}