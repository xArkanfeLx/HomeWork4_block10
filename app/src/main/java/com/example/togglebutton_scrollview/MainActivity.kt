package com.example.togglebutton_scrollview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var myTextTV:TextView
    private val database = DataBase()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myTextTV = findViewById(R.id.myTextTV)
    }

    fun onToggleClicked(view: View) {
        val on = (view as ToggleButton).isChecked
        if(on) {
            val needText = loadBook(database.someText)
            for ( i in needText) myTextTV.append("$i ")
        } else {
            myTextTV.text=""
        }
    }

    fun loadBook(text: String): List<String> {
        var list:List<String> = text.replace("[;,.?!]".toRegex(),"").split(" ")
        var resultList:List<String> = listOf()
        val size = list.size;
        val needWords = size/2

        for (i in 0..needWords) resultList+=list[(Math.random()*size-1).toInt()]
        return resultList
    }

}