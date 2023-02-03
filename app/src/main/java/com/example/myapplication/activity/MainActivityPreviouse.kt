package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityMainPreviouseBinding
import com.example.myapplication.fragment.FragmentMain


class MainActivityPreviouse : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainPreviouseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainPreviouseBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        val i = intent
        if(i!=null)bindingClass.nameSurname.text = i.getCharSequenceExtra("nameSurname")

//        bindingClass.editProfile.setOnClickListener{
//            openFragment()
//        }

        bindingClass.viewMyCont.setOnClickListener{
            var int = Intent(this, MyContactsActivity::class.java)
            startActivity(int)
        }
    }

//    private fun openFragment() {
//        val mainFragment = FragmentMain()
//        val ft = supportFragmentManager.beginTransaction()
//        ft.replace(R.id.container, mainFragment)
//        ft.commit()
//    }

}

