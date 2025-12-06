package com.kevin.proyectomovileskch.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.kevin.proyectomovileskch.R
import com.kevin.proyectomovileskch.data.model.ImageItem
import com.kevin.proyectomovileskch.ui.images.ImageListActivity
import com.kevin.proyectomovileskch.util.ImageRepository

class AddImagesActivity : AppCompatActivity() {

    private lateinit var etImageTitle: EditText
    private lateinit var imgPreview: ImageView
    private lateinit var btnSelectFromGallery: Button
    private lateinit var btnSaveImage: Button
    private lateinit var btnOpenImageList: Button

    private var selectedBitmap: Bitmap? = null

    // Launcher modern para abrir galería
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                val bitmap = loadBitmapFromUri(uri)
                if (bitmap != null) {
                    selectedBitmap = bitmap
                    imgPreview.setImageBitmap(bitmap)
                } else {
                    Toast.makeText(this, "Could not load image", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_images)

        etImageTitle = findViewById(R.id.etImageTitle)
        imgPreview = findViewById(R.id.imgPreview)
        btnSelectFromGallery = findViewById(R.id.btnSelectFromGallery)
        btnSaveImage = findViewById(R.id.btnSaveImage)
        btnOpenImageList = findViewById(R.id.btnOpenImageList)

        btnSelectFromGallery.setOnClickListener {
            // Abre la galería (solo imágenes)
            galleryLauncher.launch("image/*")
        }

        btnSaveImage.setOnClickListener {
            saveImage()
        }

        btnOpenImageList.setOnClickListener {
            startActivity(android.content.Intent(this, ImageListActivity::class.java))
        }
    }

    private fun loadBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            contentResolver.openInputStream(uri).use { input ->
                BitmapFactory.decodeStream(input)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun saveImage() {
        val title = etImageTitle.text.toString().trim()
        val bitmap = selectedBitmap

        if (title.isEmpty() || bitmap == null) {
            Toast.makeText(this, "Please select an image and enter a title", Toast.LENGTH_SHORT).show()
            return
        }

        val item = ImageItem(
            title = title,
            bitmap = bitmap
        )

        ImageRepository.addImage(item)

        Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show()

        // Limpia la UI
        etImageTitle.text.clear()
        imgPreview.setImageResource(0)
        selectedBitmap = null
    }
}
