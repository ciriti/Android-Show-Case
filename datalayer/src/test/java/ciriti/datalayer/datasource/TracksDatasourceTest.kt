@file:Suppress("IllegalIdentifier")

package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.TopTrack
import ciriti.datalayer.network.Track
import ciriti.datalayer.network.Tracks
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Created by ciriti
 */
class TracksDatasourceTest {

    @Mock lateinit var db : IDatabase
    @Mock lateinit var networAdapter : ServiceApiRx
    @Mock lateinit var topTrack : TopTrack
    @Mock lateinit var tracks : Tracks
    @Rule fun mockRules() = MockitoJUnit.rule()
    @InjectMocks lateinit var datasource : TracksDatasource

    @Test
    fun `subscription to the datasource`(){

        /** empty list */
        val list = emptyList<Track>()

        /** rules for the mock objects */
        whenever(topTrack.tracks).thenReturn(tracks)
        whenever(tracks.track).thenReturn(list)
        whenever(networAdapter.getTopTracks(10)).thenReturn(Single.just(topTrack))

        /** action to test */
        datasource.updateTopTracks(10).test()
        datasource.updateTopTracks(10).test()

        /** db is the object under test */
        verify(db, times(2)).saveCollection(list)
    }

}