package com.kevin.proyectomovileskch.data.model

import android.graphics.Bitmap
import java.util.UUID

class ImageItem(title: String, bitmap: Bitmap) {
    val id: String = UUID.randomUUID().toString()
    lateinit var title: String
    lateinit var bitmap: Bitmap
}