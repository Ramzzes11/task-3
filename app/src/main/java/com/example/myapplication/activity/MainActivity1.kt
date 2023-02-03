package com.example.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.constant.MAIN
import com.example.myapplication.databinding.ActivityMain1Binding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragment.AuthFragment1

class MainActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityMain1Binding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.transactions.setOnClickListener{
            binding.transactions.visibility = View.GONE
            binding.navigation.visibility = View.GONE
            if(savedInstanceState == null){
                val fragment = AuthFragment1()
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container1, fragment)
                    .commit()
            }
        }

        binding.navigation.setOnClickListener{
            binding.transactions.visibility = View.GONE
            binding.navigation.visibility = View.GONE
            binding.fragment.visibility = View.VISIBLE

//            val navHostFragment = supportFragmentManager
//                .findFragmentById(R.id.fragment) as NavHostFragment
//            navController = navHostFragment.navController
        }

    }

}