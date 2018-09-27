package ciriti.androidshowcase.core.util

import ciriti.datalayer.datasource.IUserDatasource
import javax.inject.Inject

/**
 * Created by ciriti
 */

interface ISession{
  fun isSessionValid(): Boolean
  fun getSessionOrNull(): String?
}

class Session @Inject constructor(
    // TODO
  userDataSource: IUserDatasource
) : ISession {

  override fun isSessionValid(): Boolean {
    // TODO insert logic to to check is the session is valid
    return true
  }

  override fun getSessionOrNull(): String? {
    // TODO fetch and get the session
    return "___session_object___"
  }
}