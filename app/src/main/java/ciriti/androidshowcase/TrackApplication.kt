package ciriti.androidshowcase

import android.app.Activity
import android.app.Application
import ciriti.androidshowcase.di.AppInjector
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
    // TODO FirebaseApp.initializeApp(this)
    LeakCanary.install(this)
    AppInjector.init(this)

  }
}