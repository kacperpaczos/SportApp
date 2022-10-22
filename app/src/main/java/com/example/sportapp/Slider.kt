package com.example.sportapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.sportapp.ui.login.LoginActivity



class Slider : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
    val editor = sharedPref.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider2)

        viewPager = findViewById(R.id.idViewPager3)
        Log.i("ViewAdapter", viewPager.toString())

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.slide1
        imageList = imageList + R.drawable.slide2

        viewPagerAdapter = ViewPagerAdapter(this@Slider, imageList)
        viewPagerAdapter.onCreate()
        viewPager.adapter = viewPagerAdapter

        val buttonClick = findViewById<Button>(R.id.goToLogin)
        buttonClick.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)

            val slajder = sharedPref.getBoolean("slajder",false)
            Log.i("SALJDER ZMIENNA", slajder.toString())
            editor.apply {
                putBoolean("slajder", true)
                apply()
            }
        }
    }
}