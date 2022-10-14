package com.internshala.qui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        lateinit var tietName : TextInputEditText
        val btnStart : Button

        tietName = findViewById(R.id.tietName)
        btnStart = findViewById(R.id.btnStart)



        btnStart.setOnClickListener {
            if(tietName.text.toString().isEmpty()){
                Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, questionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, tietName.text.toString())
                startActivity(intent)
                finish()
            }
        }



    }
}