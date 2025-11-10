package com.kevin.proyectomovileskch.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Funciones  para manejar fechas en el app.
 */
object DateUtils {

    /**
     * Convierte un timestamp a un texto legible (dd/MM/yyyy HH:mm)
     */
    fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    /**
     * Convierte fecha en texto a timestamp
     */
    fun parseDate(dateStr: String): Long {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.parse(dateStr)?.time ?: 0L
    }

    /**
     * Devuelve la fecha y hora actual en formato legible.
     */
    fun nowFormatted(): String {
        return formatDate(System.currentTimeMillis())
    }
}
