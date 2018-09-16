package ciriti.androidshowcase.di

import android.content.Context
import android.content.SharedPreferences
import ciriti.androidshowcase.BuildConfig
import ciriti.androidshowcase.TrackApplication
import ciriti.androidshowcase.core.components.DBDelegate
import ciriti.androidshowcase.core.preferences
import ciriti.androidshowcase.core.util.Navigator
import ciriti.androidshowcase.core.util.Session
import ciriti.datalayer.database.Database
import ciriti.datalayer.datasource.TracksDatasource
import ciriti.datalayer.datasource.UserDatasource
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.ServiceApiRxDelegateServiceApiRx
import ciriti.datalayer.network.Track
import ciriti.datalayer.util.NetworkManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by ciriti
 */

@Module
class ModuleDatasource {

  @Provides
  @Singleton
  fun provideNetworkManager(app: TrackApplication) = NetworkManager(app)

  @Provides
  @Singleton
  fun provideDatabase() = Database()

  /**
   * Example of delegate to implement decorator pattern for Database
   */
  @Provides
  @Singleton
  fun provideDBDelegate(
    app: TrackApplication,
    db: Database
  ) = DBDelegate(app, db)

  /**
   * Example of delegate to implement decorator pattern for NetworkAdapter
   */
  @Provides
  @Singleton
  fun provideServiceApiRxDelegateServiceApiRx(
    retrofirAdapter : ServiceApiRx
  ) = ServiceApiRxDelegateServiceApiRx(retrofirAdapter)

  @Provides
  @Singleton
  fun provideServiceApiRx(): ServiceApiRx = Retrofit.Builder().createAdapter(BuildConfig.URL)

  @Provides
  @Singleton
  fun provideTracksDatasource(
    database: DBDelegate,
    service: ServiceApiRxDelegateServiceApiRx,
    networkManager: NetworkManager
  ) = TracksDatasource(service, database, networkManager)

  @Provides
  @Singleton
  fun provideUserDatasource(
    database: Database,
    service: ServiceApiRx
  ) = UserDatasource(database = database, networAdapter = service)

  @Provides
  @Singleton
  fun providePref(app: TrackApplication): SharedPreferences = app.preferences

  @Provides
  @Singleton
  fun provideSession(userDataSource: UserDatasource) = Session(userDataSource)

  @Provides
  @Singleton
  fun provideNavigator(session: Session) = Navigator(session)

}

inline fun <reified T> Retrofit.Builder.createAdapter(url: String): T {

  val interceptor = HttpLoggingInterceptor()
  interceptor.level = when (BuildConfig.DEBUG) {
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




