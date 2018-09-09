package ciriti.androidshowcase.core

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import ciriti.androidshowcase.core.components.BaseActivity
import ciriti.androidshowcase.core.components.BaseFragment
import ciriti.androidshowcase.core.util.ImageViewBaseTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.fragmentContainer

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
inline fun <reified T : AppCompatActivity> Context.invokeActivity() {
  startActivity(Intent(this, T::class.java))
}

/**
 * Utilities for fragments
 */
fun BaseFragment.close() = fragmentManager?.popBackStack()

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun View.cancelTransition() {
  transitionName = null
}

fun AppCompatActivity.initilize(fragment: Fragment, @IdRes resId: Int) {
  supportFragmentManager
      .beginTransaction()
      .add(resId, fragment, fragment::class.java.name)
      .commit()
}

fun AppCompatActivity.openFragment(@IdRes resId: Int, fragment: Fragment) {
  supportFragmentManager
      .beginTransaction()
      .add(resId, fragment, fragment::class.java.name)
      .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
      .commit()
}

val Context.preferences: SharedPreferences
  get() = PreferenceManager.getDefaultSharedPreferences(this)

/**
 * View extensions
 */
fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.invisible() {
  this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
  LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadFromUrl(url: String) =
  Glide.with(this.context.applicationContext)
      .load(url)
      .transition(DrawableTransitionOptions.withCrossFade())
      .into(this)!!

fun ImageView.loadUrlAndPostponeEnterTransition(
  url: String,
  activity: FragmentActivity
) {
  val target: Target<Drawable> = ImageViewBaseTarget(
      this,
      activity
  )
  Glide.with(context.applicationContext)
      .load(url)
      .into(target)
}

