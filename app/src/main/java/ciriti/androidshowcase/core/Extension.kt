package ciriti.androidshowcase.core

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by ciriti
 */

/**
 * This is one file for all the extensions, if this will grow, it will be useful to create
 * different file extension for families of extensions like:
 *
 * ViewExtension.kt
 * ActivityExtension.kt
 * WhateverExtension.kt
 *
 */

fun String.Companion.empty() = ""


/**
 * Utility method to start activity
 */
inline fun<reified T : AppCompatActivity> Context.invokeActivity(){
    startActivity(Intent(this, T::class.java))
}