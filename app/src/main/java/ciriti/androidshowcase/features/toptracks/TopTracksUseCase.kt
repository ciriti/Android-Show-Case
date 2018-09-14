package ciriti.androidshowcase.features.toptracks

import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.getFlatTrack
import ciriti.datalayer.annotation.MakeItOpenForTest
import ciriti.datalayer.datasource.TracksDatasource
import io.reactivex.Flowable

/**
 * Created by ciriti
 */
@MakeItOpenForTest
class TopTracksUseCase(
  val trackDatasource: TracksDatasource
) {

  fun observeTopTrackList(): Flowable<List<FlatTrack>> {
    return trackDatasource
        .observeTrackList()
        .map {
          it.map { it1 -> it1.getFlatTrack() }
        }

  }

  fun updateTopTracks(limit: Int) = trackDatasource.loadTracks(limit)

}