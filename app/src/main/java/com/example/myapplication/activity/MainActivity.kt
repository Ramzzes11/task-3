package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.constant.MAIN
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.FragmentMain

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.fragment)

//        findNavController().navigate(
//            R.id.nav_host_fragment
//        )
        MAIN = this

//        if(savedInstanceState == null){
//            val fragment = FragmentMain()
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.container, fragment)
//                .commit()
//        }
    }
}