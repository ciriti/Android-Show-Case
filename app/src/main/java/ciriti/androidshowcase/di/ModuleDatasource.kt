package ciriti.androidshowcase.di

import android.content.Context
import android.content.SharedPreferences
import ciriti.androidshowcase.BuildConfig
import ciriti.androidshowcase.TrackApplication
import ciriti.androidshowcase.core.components.DBDelegate
import ciriti.androidshowcase.core.util.preferences
import ciriti.androidshowcase.core.util.ISession
import ciriti.androidshowcase.core.util.Session
import ciriti.datalayer.BuildConfig.DEBUG
import ciriti.datalayer.database.Database
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.datasource.ITracksDatasource
import ciriti.datalayer.datasource.IUserDatasource
import ciriti.datalayer.datasource.TracksDatasource
import ciriti.datalayer.datasource.UserDatasource
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.ServiceApiRxDelegateServiceApiRx
import ciriti.datalayer.util.INetworkManager
import ciriti.datalayer.util.NetworkManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by ciriti
 */

@Module
class ModuleDatasource {

  @Provides
  fun provideApplicationContext(
    app: TrackApplication
  ): Context = app.applicationContext

  @Provides
  @Singleton
  fun provideNetworkManager(networkManager: NetworkManager): INetworkManager = networkManager

  @Provides
  @Singleton
  fun provideDatabase(db: Database): IDatabase = db

  /**
   * Example of delegate to implement decorator pattern for Database
   */
  @Provides
  @Singleton
  @Named(value = "db_delegate")
  fun provideDBDelegate(dbDelegate : DBDelegate) :IDatabase = dbDelegate

  /**
   * Example of delegate to implement decorator pattern for NetworkAdapter
   */
  @Provides
  @Singleton
  @Named(value = "api_delegate")
  fun provideServiceApiRxDelegateServiceApiRx(
    retrofirAdapterDelegate: ServiceApiRxDelegateServiceApiRx
  ) : ServiceApiRx = retrofirAdapterDelegate

  @Provides
  @Singleton
  fun provideServiceApiRx(): ServiceApiRx = Retrofit.Builder().createAdapter(BuildConfig.URL)

  @Provides
  @Singleton
  fun provideTracksDatasource(tracksDatasource: TracksDatasource): ITracksDatasource =
    tracksDatasource

  @Provides
  @Singleton
  fun provideUserDatasource(userDs: UserDatasource): IUserDatasource = userDs

  @Provides
  @Singleton
  fun providePref(app: TrackApplication): SharedPreferences = app.preferences

  @Provides
  @Singleton
  fun provideSession(session: Session): ISession = session

}

inline fun <reified T> Retrofit.Builder.createAdapter(url: String): T {

  val interceptor = HttpLoggingInterceptor()
  interceptor.level = when (DEBUG) {
    true -> HttpLoggingInterceptor.Level.BODY
    else -> HttpLoggingInterceptor.Level.NONE
  }

  val client = OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .build()

  val builder = this
      .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
      .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
      .baseUrl(url)
      .client(client)
      .build()

  return builder.create(T::class.java)
}




