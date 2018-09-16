package ciriti.datalayer.util

import android.content.Context
import ciriti.datalayer.annotation.MakeItOpenForTest
import io.reactivex.Single

/**
 * Created by ciriti
 */
@MakeItOpenForTest
class NetworkManager(val context: Context) {
  // TODO change deprecated method
  val isConnected get() = Single.just(context.networkInfo?.isConnectedOrConnecting?:false)
}