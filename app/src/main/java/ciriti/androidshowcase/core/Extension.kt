package ciriti.androidshowcase.core

import android.content.Context
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ciriti.androidshowcase.core.components.BaseActivity
import ciriti.androidshowcase.core.components.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

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

/**
 * Utilities for fragments
 */
fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!