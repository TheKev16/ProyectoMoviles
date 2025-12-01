package com.kevin.proyectomovileskch.ui.call

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class CallingActivity : AppCompatActivity() {

    private lateinit var tvCalling: TextView
    private lateinit var imgCaller: ImageView
    private lateinit var btnMute: ImageButton
    private lateinit var btnSpeaker: ImageButton
    private lateinit var btnVideo: ImageButton
    private lateinit var btnEndCall: ImageButton

    private var isMuted = false
    private var isSpeakerOn = false
    private var isVideoOn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)

        // Tipo de llamada: "audio" o "video"
        val callType = intent.getStringExtra("CALL_TYPE") ?: "audio"

        tvCalling = findViewById(R.id.tvCalling)
        imgCaller = findViewById(R.id.imgCaller)
        btnMute = findViewById(R.id.btnMute)
        btnSpeaker = findViewById(R.id.btnSpeaker)
        btnVideo = findViewById(R.id.btnVideo)
        btnEndCall = findViewById(R.id.btnEndCall)

        tvCalling.text = if (callType == "video") {
            getString(R.string.title_video_calling)
        } else {
            getString(R.string.title_calling)
        }

        setupListeners(callType)
    }

    private fun setupListeners(callType: String) {

        btnMute.setOnClickListener {
            isMuted = !isMuted
            if (isMuted) {
                Toast.makeText(this, getString(R.string.toast_muted), Toast.LENGTH_SHORT).show()
                btnMute.setImageResource(android.R.drawable.ic_lock_silent_mode)
            } else {
                Toast.makeText(this, getString(R.string.toast_unmuted), Toast.LENGTH_SHORT).show()
                btnMute.setImageResource(android.R.drawable.ic_lock_silent_mode_off)
            }
        }

        btnSpeaker.setOnClickListener {
            isSpeakerOn = !isSpeakerOn
            if (isSpeakerOn) {
                Toast.makeText(this, getString(R.string.toast_speaker_on), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_speaker_off), Toast.LENGTH_SHORT).show()
            }
            // Aquí en una app real activarías el altavoz del teléfono
        }

        btnVideo.setOnClickListener {
            if (callType != "video") {
                // Si la llamada es de audio, solo mostramos un mensaje
                Toast.makeText(this, "Video calls are not fully implemented", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            isVideoOn = !isVideoOn
            if (isVideoOn) {
                Toast.makeText(this, getString(R.string.toast_video_on), Toast.LENGTH_SHORT).show()
                btnVideo.setImageResource(android.R.drawable.ic_menu_camera)
            } else {
                Toast.makeText(this, getString(R.string.toast_video_off), Toast.LENGTH_SHORT).show()
                btnVideo.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            }
        }

        btnEndCall.setOnClickListener {
            Toast.makeText(this, getString(R.string.toast_call_ended), Toast.LENGTH_SHORT).show()
            finish() // Cierra la pantalla de llamada
        }
    }
}
