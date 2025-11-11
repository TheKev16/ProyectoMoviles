package com.kevin.proyectomovileskch.util

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun nowIso(): String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).format(Date())
    fun buildFullName(first: String, last: String) = (first + " " + last).trim()
    fun isValidEmail(email: String?) = email?.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) ?: false
}