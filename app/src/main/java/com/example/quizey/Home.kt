package com.example.quizey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.cardview.widget.CardView


class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val cardView1 = view.findViewById<CardView>(R.id.one)
        val cardView2=view.findViewById<CardView>(R.id.two)
        val cardView3=view.findViewById<CardView>(R.id.three)


        cardView1.setOnClickListener {

            val questionsFragment = ScienceQuestions()
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, questionsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        cardView2.setOnClickListener{
            val questionsFragment = MathsQuestions()
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, questionsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        cardView3.setOnClickListener{
            val questionsFragment = Set3()
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, questionsFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }
}
