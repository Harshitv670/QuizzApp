package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val username = intent.getStringExtra(constants.USER_NAME)
        tv_name.text = username

        val totalquestions = intent.getIntExtra(constants.TOTAL_QUESTION, 0)
        val correctanswers = intent.getIntExtra(constants.CORRECT_ANSWERS,0 )
        tv_score.text = "Your Score is $correctanswers Out of $totalquestions"

        btn_finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}