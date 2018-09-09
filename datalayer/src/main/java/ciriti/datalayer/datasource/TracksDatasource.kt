package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.Track
import io.reactivex.Completable
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

interface ITracksDatasource {

  fun updateTopTracks(limit: Int): Completable

  fun observeTrackList(): BehaviorProcessor<List<Track>>

}

class TracksDatasource(
  val networAdapter: ServiceApiRx,
  val database: IDatabase
) : ITracksDatasource {

  override fun updateTopTracks(limit: Int): Completable = networAdapter
      .getTopTracks(limit)
      .map {
        database.saveCollection(it.tracks.track)
      }
      .toCompletable()

  override fun observeTrackList() = database.getCollection()
}