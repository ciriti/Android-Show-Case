package ciriti.androidshowcase.features.toptracks

import ciriti.androidshowcase.core.firstValues
import ciriti.datalayer.datasource.TracksDatasource
import ciriti.datalayer.network.TopTrack
import ciriti.datalayer.network.Track
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.processors.BehaviorProcessor
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import util.createGsonObj

/**
 * Created by ciriti
 */
class TopTracksUseCaseTest {

  @Rule fun mokitoRules() = MockitoJUnit.rule()

  @Mock lateinit var trackDatasource: TracksDatasource
  @InjectMocks lateinit var useCase: TopTracksUseCase

  @Test
  fun `test the transformation of the list`() {

    /** creating a list from a jason file */
    val list = "top_tracks.json".createGsonObj<TopTrack>()
        .tracks.track

    val processor: BehaviorProcessor<List<Track>> = BehaviorProcessor.create()
    processor.onNext(list)

    whenever(trackDatasource.loadTracks(list.size)).thenReturn(Completable.complete())
    whenever(trackDatasource.observeTrackList()).thenReturn(processor)

    val test = useCase.observeTopTrackList()
        .test()

    test.assertNoErrors()
    Assert.assertEquals(list.size, test.firstValues().size)
    Assert.assertEquals(list.first().artist?.name, test.firstValues().first().artistName)

  }

}