package com.example.quizey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MathsQuestions : Fragment() {
    private lateinit var question: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var optionGroup: RadioGroup
    private lateinit var currentQuestion: Maths.Question
    private var currentQuestionIndex = 0
    private var score = 0
    private var allQuestionsAnswered = false
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var timerTextView: TextView
    private lateinit var questionNumberTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_questions, container, false)
        question = view.findViewById(R.id.question_text)
        option1 = view.findViewById(R.id.option_1)
        option2 = view.findViewById(R.id.option_2)
        option3 = view.findViewById(R.id.option_3)
        option4 = view.findViewById(R.id.option_4)
        optionGroup = view.findViewById(R.id.options_group)
        timerTextView=view.findViewById(R.id.timer)
        questionNumberTextView=view.findViewById(R.id.question_number)
        questionNumberTextView.text = "Question 1 of 10"


        currentQuestion = Maths.questions[currentQuestionIndex]
        updateQuestion()


        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {

            if (optionGroup.checkedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val selectedOption = view.findViewById<RadioButton>(optionGroup.checkedRadioButtonId)
            val selectedOptionIndex = optionGroup.indexOfChild(selectedOption)
            if (selectedOptionIndex == currentQuestion.correctAnswer) {
                score++
            }

            currentQuestionIndex++

            if (currentQuestionIndex >= Maths.questions.size) {
                allQuestionsAnswered = true
            } else {

                currentQuestion = Maths.questions[currentQuestionIndex]
                updateQuestion()
                optionGroup.clearCheck()
            }


            val submitButton = view.findViewById<Button>(R.id.end_button)
            submitButton.isEnabled = allQuestionsAnswered

        }
        countDownTimer = object : CountDownTimer(10 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val formattedTime = String.format("%02d:%02d", minutes, seconds)
                timerTextView.text = formattedTime
            }

            override fun onFinish() {

                val submitButton = view.findViewById<Button>(R.id.end_button)
                submitButton.isEnabled=true
                submitButton.performClick()
            }
        }
        countDownTimer.start()

        val submitbtn = view.findViewById<Button>(R.id.end_button)
        submitbtn.setOnClickListener {

            val sharedPreferences = requireContext().getSharedPreferences("quiz_data", Context.MODE_PRIVATE)
            val quizId = sharedPreferences.getInt("maths_quiz_id", 0) + 1
            val quizName = "Maths Quiz"

            val quizDateTime = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault()).format(Date())

            val editor = sharedPreferences.edit()
            editor.putString("$quizName $quizId date_time", quizDateTime)
            editor.putInt("$quizName $quizId score", score)
            editor.apply()

            editor.putInt("maths_quiz_id", quizId)
            editor.apply()

            val homeFragment = Home()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, homeFragment)
            fragmentTransaction.commit()

            // Display the score
            Toast.makeText(requireContext(), "Test Submitted SuccessFully \nCheck your Result in Result Section", Toast.LENGTH_LONG).show()
        }

        return view
    }

    private fun updateQuestion() {
        // Set the question text and option buttons text
        question.text = currentQuestion.questionText
        option1.text = currentQuestion.options[0]
        option2.text = currentQuestion.options[1]
        option3.text = currentQuestion.options[2]
        option4.text = currentQuestion.options[3]
    }
}



class Maths {
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswer: Int
    )

    companion object {
        val questions = listOf(
            Question(
                "What is the value of pi (π) to two decimal places?",
                listOf("3.14", "3.16", "3.18", "3.20"),
                0
            ),
            Question(
                "What is the Pythagorean theorem?",
                listOf("a² + b² = c²", "a + b = c", "a × b = c", "a ÷ b = c"),
                0
            ),
            Question(
                "What is the slope-intercept form of a linear equation?",
                listOf("y = mx + b", "x = my + b", "y = mx - b", "x = my - b"),
                0
            ),
            Question(
                "What is the derivative of x^2?",
                listOf("2x", "x^2", "x/2", "1/x"),
                0
            ),
            Question(
                "What is the sum of the angles in a triangle?",
                listOf("180 degrees", "90 degrees", "360 degrees", "45 degrees"),
                0
            ),
            Question(
                "What is the value of e (Euler's number) to two decimal places?",
                listOf("2.71", "2.73", "2.75", "2.77"),
                0
            ),
            Question(
                "What is the formula for the area of a circle?",
                listOf("πr²", "2πr", "πd", "2πd"),
                0
            ),
            Question(
                "What is the quadratic formula?",
                listOf("(-b ± √(b² - 4ac)) / 2a", "(b ± √(b² - 4ac)) / 2a", "(-b ± √(b + 4ac)) / 2a", "(b ± √(b + 4ac)) / 2a"),
                0
            ),
            Question(
                "What is the formula for the volume of a sphere?",
                listOf("(4/3)πr³", "2πr", "πr²", "4πr²"),
                0
            ),
            Question(
                "What is the limit of sin(x)/x as x approaches 0?",
                listOf("1", "0", "infinity", "undefined"),
                0
            )
        )

    }
}
