package ciriti.androidshowcase.di

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ciriti.androidshowcase.TrackApplication
import ciriti.androidshowcase.core.components.registerActivityLifecycleCallbacks
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by Carmelo Iriti
 *
 * I got this way to do from the google samples code
 *
 * https://github.com/googlesamples/android-architecture-components
 *
 */
object AppInjector {
  fun init(app: TrackApplication) {
    DaggerAppComponent.builder()
        .create(app)
        .inject(app)
    /** I got this way to create listener from Anko library */
    app.registerActivityLifecycleCallbacks {
      _onActivityCreated { activity, _ ->
        handleActivity(activity)
      }
    }
  }

  private fun handleActivity(activity: Activity) {
    if (activity is HasSupportFragmentInjector) {
      AndroidInjection.inject(activity)
    }
    if (activity is FragmentActivity) {
      activity.supportFragmentManager
          .registerFragmentLifecycleCallbacks(
              object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                  fm: FragmentManager,
                  f: Fragment,
                  savedInstanceState: Bundle?
                ) {
                  if (f is Injectable) {
                    AndroidSupportInjection.inject(f)
                  }
                }
              }, true
          )
    }
  }
}