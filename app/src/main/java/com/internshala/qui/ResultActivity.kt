package com.internshala.qui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var tvName: TextView
    lateinit var tvScore: TextView
    lateinit var btnFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        tvName = findViewById(R.id.tvName)
        tvScore = findViewById(R.id.tvScore)
        btnFinish = findViewById(R.id.btnFinish)

        val user_name = intent.getStringExtra(Constants.USER_NAME)
        tvName.text = user_name

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        tvScore.text = "Your score is $correctAnswers out of $totalQuestions"

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}