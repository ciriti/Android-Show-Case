package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by ciriti
 */

interface IUserDatasource {
  //TODO
}

class UserDatasource @Inject constructor(
  @Named(value = "api_delegate") val networAdapter: ServiceApiRx,
  val database: IDatabase
) : IUserDatasource {
  //TODO
}