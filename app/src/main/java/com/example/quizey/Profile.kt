package com.example.quizey

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class Profile : Fragment() {
    private lateinit var b1: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        b1= view.findViewById(R.id.b1)
        b1.setOnClickListener{
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }
        return view
    }
}