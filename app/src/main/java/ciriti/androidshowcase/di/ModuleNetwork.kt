package ciriti.androidshowcase.di

import ciriti.androidshowcase.BuildConfig
import ciriti.datalayer.network.ServiceApiRx
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
abstract class ModuleNetwork {

  @Provides
  @Singleton
  fun provideServiceApiRx(networkService: ServiceApiRx) =
    Retrofit.Builder().createAdapter<ServiceApiRx>("")

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

