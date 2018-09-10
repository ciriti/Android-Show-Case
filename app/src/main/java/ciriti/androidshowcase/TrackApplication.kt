package ciriti.androidshowcase

import android.app.Activity
import android.app.Application
import ciriti.androidshowcase.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.squareup.leakcanary.LeakCanary
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TrackApplication : Application(), HasActivityInjector {

  @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
  override fun activityInjector() = activityInjector

  override fun onCreate() {
    super.onCreate()
//    FirebaseApp.initializeApp(this)

    LeakCanary.install(this)
    DaggerAppComponent.builder()
        .create(this)
        .inject(this)

  }
}