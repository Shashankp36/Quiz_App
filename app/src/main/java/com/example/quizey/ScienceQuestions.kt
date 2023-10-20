package com.example.quizey

import android.content.Context
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

class ScienceQuestions : Fragment() {
    private lateinit var question: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var optionGroup: RadioGroup
    private lateinit var currentQuestion: QuestionDataSet.Question
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



        currentQuestion = QuestionDataSet.questions[currentQuestionIndex]
        updateQuestion()


        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            // Check if an option has been selected
            if (optionGroup.checkedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check the selected option against the correct answer
            val selectedOption = view.findViewById<RadioButton>(optionGroup.checkedRadioButtonId)
            val selectedOptionIndex = optionGroup.indexOfChild(selectedOption)
            if (selectedOptionIndex == currentQuestion.correctAnswer) {
                score++
            }


            currentQuestionIndex++

            if (currentQuestionIndex >= QuestionDataSet.questions.size) {
                allQuestionsAnswered = true
            } else {
                // Get the new current question and update the UI
                currentQuestion = QuestionDataSet.questions[currentQuestionIndex]
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
                submitButton.performClick() // Automatically click the submit button when the timer finishes
            }
        }

        // Start the countdown timer
        countDownTimer.start()

        val submitbtn = view.findViewById<Button>(R.id.end_button)
        submitbtn.setOnClickListener {
            val sharedPreferences = requireContext().getSharedPreferences("quiz_data", Context.MODE_PRIVATE)
            val quizId = sharedPreferences.getInt("science_quiz_id", 0) + 1
            val quizName = "Science Quiz"

            val quizDateTime = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault()).format(Date())

            val editor = sharedPreferences.edit()
            editor.putString("$quizName $quizId date_time", quizDateTime)
            editor.putInt("$quizName $quizId score", score)
            editor.apply()


            editor.putInt("science_quiz_id", quizId)
            editor.apply()

            val homeFragment = Home()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, homeFragment)
            fragmentTransaction.commit()
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



class QuestionDataSet {
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswer: Int
    )

    companion object {
        val questions = listOf(
            Question(
                "What is the smallest unit of matter?",
                listOf("Atom", "Molecule", "Electron", "Neutron"),
                0
            ),
            Question(
                "What is the largest organ in the human body?",
                listOf("Heart", "Liver", "Lungs", "Skin"),
                3
            ),
            Question(
                "What is the process by which plants convert light energy into chemical energy?",
                listOf("Respiration", "Photosynthesis", "Fermentation", "Combustion"),
                1
            ),
            Question(
                "Which gas makes up the majority of the Earth's atmosphere?",
                listOf("Oxygen", "Carbon dioxide", "Nitrogen", "Methane"),
                2
            ),
            Question(
                "What is the formula for water?",
                listOf("CO2", "H2O", "CH4", "O2"),
                1
            ),
            Question(
                "What is the process by which cells convert glucose into energy?",
                listOf("Fermentation", "Photosynthesis", "Respiration", "Combustion"),
                2
            ),
            Question(
                "What is the name of the force that opposes motion between two surfaces in contact?",
                listOf("Gravity", "Friction", "Momentum", "Inertia"),
                1
            ),
            Question(
                "What is the name of the layer of the Earth's atmosphere where most weather occurs?",
                listOf("Troposphere", "Stratosphere", "Mesosphere", "Thermosphere"),
                0
            ),
            Question(
                "What is the name of the force that causes objects to fall towards the Earth?",
                listOf("Momentum", "Inertia", "Gravity", "Acceleration"),
                2
            ),
            Question(
                "What is the name of the process by which solid ice turns into water vapor without melting?",
                listOf("Sublimation", "Condensation", "Evaporation", "Melting"),
                0
            )
        )

    }
}




