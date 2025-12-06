package com.kevin.proyectomovileskch.util

import com.kevin.proyectomovileskch.data.model.ImageItem

object ImageRepository {
    private val images: MutableList<ImageItem> = mutableListOf()

    fun addImage(item: ImageItem) {
        images.add(item)
    }

    fun getImages(): List<ImageItem> = images.toList()
}