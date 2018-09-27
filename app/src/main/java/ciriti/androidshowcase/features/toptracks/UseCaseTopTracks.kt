package ciriti.androidshowcase.features.toptracks

import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.util.getFlatTrack
import ciriti.datalayer.annotation.MakeItOpenForTest
import ciriti.datalayer.datasource.ITracksDatasource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by ciriti
 */

interface IUseCaseTopTracks {
  fun observeTopTrackList(): Flowable<List<FlatTrack>>
  fun updateTopTracks(limit: Int): Completable
}

@MakeItOpenForTest
class UseCaseTopTracks @Inject constructor(
  protected val trackDatasource: ITracksDatasource
) : IUseCaseTopTracks {

  override fun observeTopTrackList(): Flowable<List<FlatTrack>> {
    return trackDatasource
        .observeTrackList()
        .map {
          it.map { it1 -> it1.getFlatTrack() }
        }

  }

  override fun updateTopTracks(limit: Int) = trackDatasource.loadTracks(limit)

}