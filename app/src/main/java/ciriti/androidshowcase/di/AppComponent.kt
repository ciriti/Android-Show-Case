package ciriti.androidshowcase.di

import ciriti.androidshowcase.TrackApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by ciriti
 */
@Singleton
@Component(
    modules = [
      ModuleActivity::class,
      ModuleViewModel::class,
      ModuleDatasource::class,
      ModuleUsecase::class
    ]
)
interface AppComponent : AndroidInjector<TrackApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<TrackApplication>()

}