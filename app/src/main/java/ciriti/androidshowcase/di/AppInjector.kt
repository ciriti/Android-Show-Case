package ciriti.androidshowcase.di

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import ciriti.androidshowcase.TrackApplication
import ciriti.androidshowcase.core.components.registerActivityLifecycleCallbacks
import ciriti.androidshowcase.core.components.registerFragmentLifecycleCallbacks
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * Created by Carmelo Iriti
 *
 * I got this way to do from the google samples code
 * I manage the listener as in Anko library
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
                handleActivity1(activity)
            }
        }
    }

    private fun handleActivity1(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks {
                onFragmentAttached_ { _, f, _ ->
                    if (f is Injectable) AndroidSupportInjection.inject(f)
                }
            }
        }
    }
}