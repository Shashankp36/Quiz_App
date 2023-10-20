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


class Set3 : Fragment() {
    private lateinit var question: TextView
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private lateinit var optionGroup: RadioGroup
    private lateinit var currentQuestion: Set.Question
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


        // Load the first question
        currentQuestion = Set.questions[currentQuestionIndex]
        updateQuestion()

        // Set click listener to the next button
        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            // Check if an option has been selected
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

            if (currentQuestionIndex >= Set.questions.size) {
                allQuestionsAnswered = true
            } else {

                currentQuestion = Set.questions[currentQuestionIndex]
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


            val quizId = sharedPreferences.getInt("dsa_quiz_id", 0) + 1

            val quizName = "DSA Quiz"
            val quizDateTime = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault()).format(Date())


            val editor = sharedPreferences.edit()
            editor.putString("$quizName $quizId date_time", quizDateTime)
            editor.putInt("$quizName $quizId score", score)
            editor.apply()


            editor.putInt("dsa_quiz_id", quizId)
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

        question.text = currentQuestion.questionText
        option1.text = currentQuestion.options[0]
        option2.text = currentQuestion.options[1]
        option3.text = currentQuestion.options[2]
        option4.text = currentQuestion.options[3]
    }
}



class Set {
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswer: Int
    )

    companion object {
        val questions = listOf(
            Question(
                "What is the time complexity of inserting an element in a hash set?",
                listOf("O(1)", "O(n)", "O(log n)", "O(n log n)"),
                0
            ),
            Question(
                "What is the difference between a stack and a queue?",
                listOf("Order of insertion", "Order of removal", "Both", "None"),
                2
            ),
            Question(
                "What is the time complexity of searching an element in an array?",
                listOf("O(1)", "O(n)", "O(log n)", "O(n log n)"),
                1
            ),
            Question(
                "What is the time complexity of sorting n elements using bubble sort?",
                listOf("O(n)", "O(n log n)", "O(n²)", "O(2ⁿ)"),
                2
            ),
            Question(
                "What data structure is used to implement a priority queue?",
                listOf("Heap", "Stack", "Queue", "List"),
                0
            ),
            Question(
                "What is the time complexity of merging two sorted arrays of size n and m?",
                listOf("O(n + m)", "O(n log n)", "O(n²)", "O(2ⁿ)"),
                0
            ),
            Question(
                "What is the worst case time complexity of quicksort?",
                listOf("O(n)", "O(n log n)", "O(n²)", "O(2ⁿ)"),
                2
            ),
            Question(
                "What is the space complexity of depth-first search on a graph with n nodes?",
                listOf("O(1)", "O(log n)", "O(n)", "O(n²)"),
                2
            ),
            Question(
                "What is the difference between a binary tree and a binary search tree?",
                listOf("Number of nodes", "Order of nodes", "Both", "None"),
                1
            ),
            Question(
                "What is the time complexity of finding the shortest path between two nodes in a graph using Dijkstra's algorithm?",
                listOf("O(1)", "O(log n)", "O(n)", "O(n log n)"),
                3
            )
        )

    }
}