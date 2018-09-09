package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.exception.NoNetworkException
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.Track
import ciriti.datalayer.util.NetworkManager
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

/**
 * Created by ciriti
 */

interface ITracksDatasource {

  fun updateTopTracks(limit: Int): Completable

  fun observeTrackList(): BehaviorProcessor<List<Track>>

}

class TracksDatasource(
  protected val networAdapter: ServiceApiRx,
  protected val database: IDatabase,
  protected val networkManager: NetworkManager
) : ITracksDatasource {

  init {
    println()
  }

  override fun updateTopTracks(limit: Int): Completable =
    networkManager
        .isConnected
        .flatMap {
          if (it) throw NoNetworkException()
          networAdapter.getTopTracks(limit)
        }
        .map {
          database.saveCollection(it.tracks.track)
        }
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())

        .toCompletable()

  override fun observeTrackList() = database.getCollection()
}