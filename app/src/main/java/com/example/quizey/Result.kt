package com.example.quizey

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Result : Fragment() {

    private lateinit var scienceQuizNameTextView: TextView
    private lateinit var scienceQuizDateTimeTextView: TextView
    private lateinit var scienceQuizScoreTextView: TextView
    private lateinit var mathsQuizNameTextView: TextView
    private lateinit var mathsQuizDateTimeTextView: TextView
    private lateinit var mathsQuizScoreTextView: TextView
    private lateinit var dsaQuizNameTextView: TextView
    private lateinit var dsaQuizDateTimeTextView: TextView
    private lateinit var dsaQuizScoreTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        scienceQuizNameTextView = view.findViewById(R.id.quiz_name_text_view)
        scienceQuizDateTimeTextView = view.findViewById(R.id.quiz_date_time_text_view)
        scienceQuizScoreTextView = view.findViewById(R.id.quiz_score_text_view)
        mathsQuizNameTextView = view.findViewById(R.id.quiz_name_text_view1)
        mathsQuizDateTimeTextView = view.findViewById(R.id.quiz_date_time_text_view1)
        mathsQuizScoreTextView = view.findViewById(R.id.quiz_score_text_view1)

         dsaQuizNameTextView = view.findViewById(R.id.quiz_name_text_view2)
         dsaQuizDateTimeTextView = view.findViewById(R.id.quiz_date_time_text_view2)
         dsaQuizScoreTextView = view.findViewById(R.id.quiz_score_text_view2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val scienceSharedPreferences = requireContext().getSharedPreferences("quiz_data", Context.MODE_PRIVATE)
        val scienceQuizId = scienceSharedPreferences.getInt("science_quiz_id", 0)
        val scienceQuizName = "Science Quiz $scienceQuizId"
        val scienceQuizDateTime = scienceSharedPreferences.getString("$scienceQuizName date_time", "")
        val scienceQuizScore = scienceSharedPreferences.getInt("$scienceQuizName score", 0)


        scienceQuizNameTextView.text = scienceQuizName
        scienceQuizDateTimeTextView.text = scienceQuizDateTime
        scienceQuizScoreTextView.text = "Your score is $scienceQuizScore"

        // Retrieve the Maths Quiz data from SharedPreferences
        val mathsSharedPreferences = requireContext().getSharedPreferences("quiz_data", Context.MODE_PRIVATE)
        val mathsQuizId = mathsSharedPreferences.getInt("maths_quiz_id", 0)
        val mathsQuizName = "Maths Quiz $mathsQuizId"
        val mathsQuizDateTime = mathsSharedPreferences.getString("$mathsQuizName date_time", "")
        val mathsQuizScore = mathsSharedPreferences.getInt("$mathsQuizName score", 0)


        mathsQuizNameTextView.text = mathsQuizName
        mathsQuizDateTimeTextView.text = mathsQuizDateTime
        mathsQuizScoreTextView.text = "Your score is $mathsQuizScore"

        val dsaSharedPreferences = requireContext().getSharedPreferences("quiz_data", Context.MODE_PRIVATE)
        val dsaQuizId = dsaSharedPreferences.getInt("dsa_quiz_id", 0)
        val dsaQuizName = "DSA Quiz $dsaQuizId"
        val dsaQuizDateTime = dsaSharedPreferences.getString("$dsaQuizName date_time", "")
        val dsaQuizScore = dsaSharedPreferences.getInt("$dsaQuizName score", 0)



        dsaQuizNameTextView.text = dsaQuizName
        dsaQuizDateTimeTextView.text = dsaQuizDateTime
        dsaQuizScoreTextView.text = "Your score is $dsaQuizScore"
    }

}
