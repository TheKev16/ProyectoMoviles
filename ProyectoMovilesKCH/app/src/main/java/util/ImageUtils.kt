package util

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.IOException

/**
 * Funciones para manejar imágenes desde la galería o cámara.
 */
object ImageUtils {

    const val REQUEST_IMAGE_GALLERY = 1001
    const val REQUEST_IMAGE_CAMERA = 1002

    /**
     * Abre la galería del dispositivo para seleccionar una imagen.
     */
    fun openGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
    }

    /**
     * Abre la cámara para capturar una imagen nueva.
     */
    fun openCamera(activity: Activity) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity.startActivityForResult(intent, REQUEST_IMAGE_CAMERA)
    }

    /**
     * Convierte un URI a Bitmap (para mostrar la imagen en un ImageView).
     */
    fun getBitmapFromUri(activity: Activity, uri: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
