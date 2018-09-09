package ciriti.androidshowcase.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import ciriti.androidshowcase.features.ViewModelFactory
import ciriti.androidshowcase.features.toptracks.TopTracksViewModel
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
  @ViewModelKey(TopTracksViewModel::class)
  abstract fun bindTopTracksViewModel(vm: TopTracksViewModel): ViewModel

}