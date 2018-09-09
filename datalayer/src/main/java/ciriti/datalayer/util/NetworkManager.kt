package ciriti.datalayer.util

import android.content.Context
import io.reactivex.Single

/**
 * Created by ciriti
 */
class NetworkManager(val context: Context) {
  // TODO change deprecated method
  val isConnected get() = Single.just(context.networkInfo?.isConnectedOrConnecting)
}