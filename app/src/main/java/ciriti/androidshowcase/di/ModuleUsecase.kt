package ciriti.androidshowcase.di

import ciriti.androidshowcase.features.toptracks.TopTracksUseCase
import ciriti.datalayer.datasource.TracksDatasource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ciriti
 */
@Module
class ModuleUsecase {

    @Provides
    @Singleton
    fun provideTopTracksUseCase(trackDs : TracksDatasource) = TopTracksUseCase(trackDs)

}