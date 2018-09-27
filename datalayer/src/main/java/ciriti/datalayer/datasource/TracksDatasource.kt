package ciriti.datalayer.datasource

import ciriti.datalayer.annotation.MakeItOpenForTest
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.exception.NoNetworkException
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.Track
import ciriti.datalayer.util.INetworkManager
import io.reactivex.Completable
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by ciriti
 */

interface ITracksDatasource {

  fun loadTracks(limit: Int): Completable

  fun observeTrackList(): BehaviorProcessor<List<Track>>

}

@MakeItOpenForTest
class TracksDatasource @Inject constructor(
  @Named(value = "api_delegate") protected val networAdapter: ServiceApiRx,
  @Named(value = "db_delegate") protected val database: IDatabase,
  protected val networkManager: INetworkManager
) : ITracksDatasource {

  override fun loadTracks(limit: Int): Completable =
    networkManager
        .isConnected
        .flatMap {
          if (!it) throw NoNetworkException()
          networAdapter.getTopTracks(limit)
        }
        .map {
          database.saveCollection(it.tracks.list)
        }
        .toCompletable()

  override fun observeTrackList() = database.getCollection()
}