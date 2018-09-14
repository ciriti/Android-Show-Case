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
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.components.BaseActivity
import ciriti.androidshowcase.core.components.BaseFragment
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.util.ImageViewBaseTarget
import ciriti.androidshowcase.features.BaseViewModel
import ciriti.datalayer.exception.NoNetworkException
import ciriti.datalayer.network.Track
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import kotlinx.android.synthetic.main.activity_main.fragmentContainer
import java.security.InvalidParameterException

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

val BaseFragment.viewContainer: View get() = (activity as AppCompatActivity).fragmentContainer

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

/**
 * Rx extension
 */
fun <T> Single<T>.subscribeOnWorkerT() = subscribeOn(Schedulers.computation())
fun <T> Single<T>.observeOnAndroidMT() = observeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.subscribeOnWorkerT() = subscribeOn(Schedulers.computation())
fun <T> Flowable<T>.observeOnAndroidMT() = observeOn(AndroidSchedulers.mainThread())
fun Completable.subscribeOnWorkerT() = subscribeOn(Schedulers.computation())
fun Completable.observeOnAndroidMT() = observeOn(AndroidSchedulers.mainThread())

fun Track.getFlatTrack(): FlatTrack {

  val array = Array(4) { "" }
  this.image
      .forEachIndexed { index, image ->
        array[index] = image.text ?: ""
      }

  return FlatTrack(
      name = this.name,
      artistName = this.artist?.name ?: "",
      artistUrl = this.artist?.url ?: "",
      duration = this.duration,
      imageUrl_S = array[0],
      imageUrl_M = array[1],
      imageUrl_L = array[2],
      imageUrl_XL = array[3],
      listeners = this.listeners,
      mbid = this.mbid,
      playcount = this.playcount,
      url = this.url
  )
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
  this.add(disposable)
}

/**
 * Map the right message to the exception
 */
fun BaseViewModel.handleException(error: Throwable) = when (error) {
  is NoNetworkException -> R.string.offline_active
  else -> R.string.generic_error
}

/**
 * RxJava test extension - return the first event received
 */
fun <T> TestSubscriber<T>.firstValues() = this.values().first()