package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import javax.inject.Named

/**
 * Created by ciriti
 */

interface IArtistsDatasource {
  //TODO
}

class ArtistsDatasource(
  @Named(value = "api_delegate") val networAdapter: ServiceApiRx,
  @Named(value = "db_delegate") val database: IDatabase
) : IArtistsDatasource {
  //TODO
}