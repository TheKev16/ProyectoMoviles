package com.kevin.proyectomovileskch.ui.message

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R

class MessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val tvTitle = findViewById<TextView>(R.id.tvMessageTitle)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<Button>(R.id.btnSendMessage)

        val doctorId = intent.getStringExtra("doctorId")
        tvTitle.text = getString(R.string.title_send_message) + (doctorId?.let { " - $it" } ?: "")

        btnSend.setOnClickListener {
            val msg = etMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                // In-memory: just show toast. Persist with controllers if needed.
                Toast.makeText(this, getString(R.string.toast_message_sent), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
