package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers:Int = 0
    private var mUserName:String? = null



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(constants.USER_NAME)

        mQuestionsList = constants.getQuestion()

        setQuestions()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestions(){

        val questions = mQuestionsList!![mCurrentPosition-1]

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" +"/"+ progressBar.max

        tv_question.text = questions!!.question
        tv_option_one.text = questions.optionOne
        tv_option_two.text = questions.optionTwo
        tv_option_three.text = questions.optionThree
        tv_option_four.text = questions.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for(options in options){
            options.setTextColor(Color.parseColor("#7A8089"))
            options.typeface = Typeface.DEFAULT
            options.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.tv_option_one ->{selectOptionView(tv_option_one,1)}
            R.id.tv_option_two ->{selectOptionView(tv_option_two,2)}
            R.id.tv_option_three ->{selectOptionView(tv_option_three,3)}
            R.id.tv_option_four  ->{selectOptionView(tv_option_four,4)}
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestions()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(constants.USER_NAME, mUserName)
                            intent.putExtra(constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(constants.TOTAL_QUESTION, mQuestionsList!!.size)
                            startActivity(intent)

                        }
                    }
                }else{
                    val questions = mQuestionsList?.get(mCurrentPosition - 1)
                    if (questions!!.CorrectAns !=  mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(questions.CorrectAns,R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text = "FINISH"
                    }else{
                        btn_submit.text  = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                    }
            }
        }
    }

    private fun answerView(answer:Int, drawableView:Int){
        when (answer){
            1 -> {tv_option_one.background= ContextCompat.getDrawable(this,drawableView)}
            2 -> {tv_option_two.background= ContextCompat.getDrawable(this,drawableView)}
            3 -> {tv_option_three.background= ContextCompat.getDrawable(this,drawableView)}
            4 -> {tv_option_four.background= ContextCompat.getDrawable(this,drawableView)}

        }
    }

    private fun selectOptionView(tv:TextView, selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD )
        tv.typeface = Typeface.DEFAULT
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }
}