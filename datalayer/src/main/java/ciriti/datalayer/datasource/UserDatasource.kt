package ciriti.datalayer.datasource

import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx

/**
 * Created by ciriti
 */

interface IUserDatasource{
  //TODO
 }

class UserDatasource(
        val networAdapter : ServiceApiRx,
        val database : IDatabase
) : IUserDatasource{
    //TODO
}