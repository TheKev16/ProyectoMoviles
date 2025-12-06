package com.kevin.proyectomovileskch.ui.images

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.util.ImageRepository

class ImageListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        val rvImages = findViewById<RecyclerView>(R.id.rvImages)
        rvImages.layoutManager = LinearLayoutManager(this)

        val images = ImageRepository.getImages()
        val adapter = ImageAdapter(images)
        rvImages.adapter = adapter
    }
}
