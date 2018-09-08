@file:Suppress("IllegalIdentifier")

package ciriti.datalayer.network

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import util.createAdapter

/**
 * Created by ciriti
 */
class ServiceApiRxTest {

    lateinit var retrofit : ServiceApiRx

    @Before
    fun setup(){
        retrofit = Retrofit.Builder().createAdapter(URL)
    }

    @Test
    fun `test resp network adapter`(){
        var test = retrofit.getTopTracks(10).test()
        Assert.assertEquals(10,   ((test.events[0][0]) as TopTrack).tracks.track.size)
        test.assertNoErrors()
    }
}
