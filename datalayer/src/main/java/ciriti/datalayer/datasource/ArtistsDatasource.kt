package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx

/**
 * Created by ciriti
 */

interface IArtistsDatasource {
  //TODO
}

class ArtistsDatasource(
  val networAdapter: ServiceApiRx,
  val database: IDatabase
) : IArtistsDatasource {
  //TODO
}