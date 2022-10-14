package com.internshala.qui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class questionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var progressbar: ProgressBar
    lateinit var tv_progress: TextView
    lateinit var tvQuestion: TextView
    lateinit var ivImage: ImageView
    lateinit var tv_option_one: TextView
    lateinit var tv_option_two: TextView
    lateinit var tv_option_three: TextView
    lateinit var tv_option_four: TextView
    lateinit var btnSubmit: Button


    private var mCurrentPosition:Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        supportActionBar?.hide()

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressbar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tvProgress)
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.imgFlag)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        mQuestionList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener (this)
        tv_option_two.setOnClickListener (this)
        tv_option_three.setOnClickListener (this)
        tv_option_four.setOnClickListener (this)
        btnSubmit.setOnClickListener (this)



    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one -> {
                selectedOptionNumber(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionNumber(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionNumber(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionNumber(tv_option_four, 4)
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
                            startActivity(intent)
                            finish()

                        }
                    }
                } else {
                        val question = mQuestionList?.get(mCurrentPosition-1)
                        if(question!!.correctOption != mSelectedOptionPosition){
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_background)
                        }else{
                            mCorrectAnswers++
                        }
                        answerView(question.correctOption, R.drawable.correct_option_background)

                    if(mCurrentPosition == mQuestionList!!.size){
                        btnSubmit.text = "FINISH"
                    }else{
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

        }
    }

    private fun setQuestion(){


        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionList!!.size){
            btnSubmit.text = "FINISH"
        }else{
            btnSubmit.text = "SUBMIT"
        }

        progressbar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressbar.max
        tvQuestion.text = question.question
        ivImage.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour

    }

    private fun selectedOptionNumber(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@questionActivity,
            R.drawable.selected_option_background_bg
        )
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@questionActivity,
                R.drawable.default_option_background_bg
            )
        }
    }

}