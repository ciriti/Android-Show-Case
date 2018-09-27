package ciriti.datalayer.util

import android.content.Context
import ciriti.datalayer.annotation.MakeItOpenForTest
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by ciriti
 */

interface INetworkManager {
  val isConnected: Single<Boolean>
}

@MakeItOpenForTest
class NetworkManager @Inject constructor(val context: Context) : INetworkManager {
  // TODO change deprecated method
  override val isConnected
    get() = Single.just(
        context.networkInfo?.isConnectedOrConnecting ?: false
    )
}