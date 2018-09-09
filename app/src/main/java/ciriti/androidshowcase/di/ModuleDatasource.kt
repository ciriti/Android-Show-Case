package ciriti.androidshowcase.di

import android.content.SharedPreferences
import ciriti.androidshowcase.TrackApplication
import ciriti.androidshowcase.core.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.datasource.TracksDatasource
import ciriti.datalayer.network.ServiceApiRx
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ciriti
 */

@Module
class ModuleDatasource {

  @Provides
  @Singleton
  fun provideTracksDatasource(
    database: IDatabase,
    service: ServiceApiRx
  ) = TracksDatasource(database = database, networAdapter = service)

  @Provides
  @Singleton
  fun providePref(app: TrackApplication): SharedPreferences = app.preferences

}


