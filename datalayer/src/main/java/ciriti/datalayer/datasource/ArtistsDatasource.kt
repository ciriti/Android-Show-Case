package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import ciriti.datalayer.network.Track
import io.reactivex.Completable
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

interface IArtistsDatasource{
    //TODO
 }

class ArtistsDatasource(
        val networAdapter : ServiceApiRx,
        val database : IDatabase
) : IArtistsDatasource{
    //TODO
}