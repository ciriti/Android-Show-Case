package ciriti.androidshowcase.di

import ciriti.androidshowcase.features.toptracks.TopTracksActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ciriti
 */
@Module
abstract class ModuleActivity {

  /**
   * An AndroidInjector will be created the for the TopTracksActivity.
   */
  @ScopeActivity
  @ContributesAndroidInjector(modules = [ModuleFragment::class])
  abstract fun TopTracksActivityInjector(): TopTracksActivity

}