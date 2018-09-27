package ciriti.androidshowcase.di

import ciriti.androidshowcase.features.toptracks.IUseCaseTopTracks
import ciriti.androidshowcase.features.toptracks.UseCaseTopTracks
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
    fun provideTopTracksUseCase(topTracksUseCase : UseCaseTopTracks) : IUseCaseTopTracks = topTracksUseCase

}