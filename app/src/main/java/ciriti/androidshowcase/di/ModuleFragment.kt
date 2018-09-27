package ciriti.androidshowcase.di

import ciriti.androidshowcase.features.toptracks.TopTracksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ciriti
 */
@Module
abstract class ModuleFragment {

  @ContributesAndroidInjector
  abstract fun topTracksFragmentInjector(): TopTracksFragment

}