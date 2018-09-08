package ciriti.datalayer.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

/**
 * Created by carmeloiriti, 05/01/18.
 */
class ServiceApiRxTest {

    lateinit var retrofit : ServiceApiRx
    @Rule
    fun mokitoRules() = MockitoJUnit.rule()

    @Before
    fun setup(){

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .baseUrl(URL)
                .client(client)
                .build()
                .create(ServiceApiRx::class.java)
    }

    @Test
    fun getgetTopArtistsTest(){
        var test = retrofit.getTopTracks(10).test()
        Assert.assertEquals(10,   ((test.events[0][0]) as List<TopTrack>).size)
        test.assertNoErrors()
    }
}