package ciriti.androidshowcase.features.toptracks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.initilize
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksActivity : AppCompatActivity(), HasSupportFragmentInjector {

  @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

  init {

  }

  override fun onCreate(savedInstanceState: Bundle?) {

    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initilize(TopTracksFragment(), R.id.fragmentContainer)
  }

}