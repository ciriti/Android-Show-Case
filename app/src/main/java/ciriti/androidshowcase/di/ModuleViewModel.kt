package ciriti.androidshowcase.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ciriti.androidshowcase.core.components.ViewModelFactory
import ciriti.androidshowcase.features.toptracks.ViewModelTopTracks
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by ciriti
 */
@Module
abstract class ModuleViewModel {

  @Binds
  @Singleton
  abstract fun bindViewModelFactory(vmFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @Singleton
  @ViewModelKey(ViewModelTopTracks::class)
  abstract fun bindTopTracksViewModel(vm: ViewModelTopTracks): ViewModel

}