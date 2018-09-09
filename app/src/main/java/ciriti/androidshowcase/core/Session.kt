package ciriti.androidshowcase.core

import ciriti.datalayer.datasource.IUserDatasource

/**
 * Created by ciriti
 */
class Session(
        // TODO
        userDataSource : IUserDatasource
) {

    fun isSessionValid() : Boolean{
        // TODO insert logic to to check is the session is valid
        return true
    }

    fun getSessionOrNull() : String? {
        // TODO fetch and get the session
        return  "___session_object___"
    }
}