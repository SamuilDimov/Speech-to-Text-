package com.example.speechtotext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import java.util.*
import android.widget.TextView
import kotlin.collections.ArrayList
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 1
    private lateinit var idIVMic: ImageView
    private lateinit var idTVOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views manually
        idIVMic = findViewById(R.id.idIVMic)
        idTVOutput = findViewById(R.id.idTVOutput)

        idIVMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            //one can choose specific language but its on default right now
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to Text")
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                Toast.makeText(this, "" + e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if function is successful
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                //display output speech to text in textview
                idTVOutput.text = res[0]
            }
        }
    }
}