package com.kevin.proyectomovileskch.ui.call

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class CallingActivity : AppCompatActivity() {

    private var isMuted = false
    private var isSpeaker = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)

        val btnMute = findViewById<ImageButton>(R.id.btnMute)
        val btnSpeaker = findViewById<ImageButton>(R.id.btnSpeaker)
        val btnEnd = findViewById<ImageButton>(R.id.btnEndCall)
        val tvCalling = findViewById<TextView>(R.id.tvCalling)
        val imgCaller = findViewById<ImageView>(R.id.imgCaller)

        val doctorId = intent.getStringExtra("doctorId")
        tvCalling.text = getString(R.string.title_calling) + (doctorId?.let { " - $it" } ?: "")

        btnMute.setOnClickListener {
            isMuted = !isMuted
            // Toggle icon or visual feedback
            btnMute.alpha = if (isMuted) 0.4f else 1f
        }
        btnSpeaker.setOnClickListener {
            isSpeaker = !isSpeaker
            btnSpeaker.alpha = if (isSpeaker) 1f else 0.4f
        }
        btnEnd.setOnClickListener {
            finish() // End call and go back
        }
    }
}
