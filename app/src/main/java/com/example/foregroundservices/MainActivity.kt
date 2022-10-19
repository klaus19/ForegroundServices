package com.example.foregroundservices

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.foregroundservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnStart.setOnClickListener {
            startService()
        }
        binding.btnStop.setOnClickListener {
            stopService()
        }

    }



    public fun startService() {
        val input = binding.editTextInput.text.toString()

        val myServiceIntent = Intent(this, TimerService::class.java)
        myServiceIntent.putExtra(Constants.inputExtra, input)
        ContextCompat.startForegroundService(this, myServiceIntent)
    }

    public fun stopService() {
        val serviceIntent = Intent(this, TimerService::class.java)
        stopService(serviceIntent)
    }
}