package com.example.myapplication.model

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.Coil
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide


fun View.getInCircleGlide(url: String, view: ImageView) {
    Glide.with(this).load(url).circleCrop().into(view)
}

fun Fragment.getInCircleGlide(url: String, view: ImageView){
    Glide.with(this).load(url).circleCrop().into(view)
}

fun View.getInCircleCoil(url: String, view: ImageView) {
    view.load(url){
        transformations(CircleCropTransformation())
    }
}

fun Fragment.getInCircleCoil(url: String, view: ImageView) {
    view.load(url){
        transformations(CircleCropTransformation())
    }
}