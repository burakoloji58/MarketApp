package com.example.marketapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var ulas : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        ulas = ActivityIntroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ulas.root)

        ulas.StartButtonCl.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}