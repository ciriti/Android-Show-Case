package ciriti.androidshowcase.core.components

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

/**
 * Created by Carmelo Iriti
 *
 * I got this technique from Anko library (TextWatcher implementation)
 *
 */
class LifeCycleCallback : ActivityLifecycleCallbacks {

  override fun onActivityPaused(activity: Activity?) {
    _onActivityPaused?.invoke(activity)
  }

  override fun onActivityResumed(activity: Activity?) {
    _onActivityResumed?.invoke(activity)
  }

  override fun onActivityStarted(activity: Activity?) {
    _onActivityStarted?.invoke(activity)
  }

  override fun onActivityDestroyed(activity: Activity?) {
    _onActivityDestroyed?.invoke(activity)
  }

  override fun onActivitySaveInstanceState(
    activity: Activity?,
    outState: Bundle?
  ) {
    _onActivitySaveInstanceState?.invoke(activity, outState)
  }

  override fun onActivityStopped(activity: Activity?) {
    _onActivityStopped?.invoke(activity)
  }

  override fun onActivityCreated(
    activity: Activity,
    savedInstanceState: Bundle?
  ) {
    _onActivityCreated?.invoke(activity, savedInstanceState)
  }

  private var _onActivityPaused: ((activity: Activity?) -> Unit)? = null

  fun _onActivityPaused(func: (activity: Activity?) -> Unit) {
    _onActivityPaused = func
  }

  private var _onActivityResumed: ((activity: Activity?) -> Unit)? = null

  fun _onActivityResumed(func: (activity: Activity?) -> Unit) {
    _onActivityResumed = func
  }

  private var _onActivityStarted: ((activity: Activity?) -> Unit)? = null

  fun _onActivityStarted(func: (activity: Activity?) -> Unit) {
    _onActivityStarted = func
  }

  private var _onActivityDestroyed: ((activity: Activity?) -> Unit)? = null

  fun _onActivityDestroyed(func: (activity: Activity?) -> Unit) {
    _onActivityDestroyed = func
  }

  private var _onActivitySaveInstanceState: ((activity: Activity?, outState: Bundle?) -> Unit)? =
    null

  fun _onActivitySaveInstanceState(func: (activity: Activity?, outState: Bundle?) -> Unit) {
    _onActivitySaveInstanceState = func
  }

  private var _onActivityStopped: ((activity: Activity?) -> Unit)? = null

  fun _onActivityStopped(func: (activity: Activity?) -> Unit) {
    _onActivityStopped = func
  }

  private var _onActivityCreated: ((activity: Activity, savedInstanceState: Bundle?) -> Unit)? =
    null

  fun _onActivityCreated(func: (activity: Activity, savedInstanceState: Bundle?) -> Unit) {
    _onActivityCreated = func
  }

}

fun Application.registerActivityLifecycleCallbacks(init: LifeCycleCallback.() -> Unit): ActivityLifecycleCallbacks {
  val listener = LifeCycleCallback()
  listener.init()
  registerActivityLifecycleCallbacks(listener)
  return listener
}