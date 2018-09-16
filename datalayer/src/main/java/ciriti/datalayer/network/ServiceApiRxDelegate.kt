package ciriti.datalayer.network

/**
 * Created by ciriti
 */
/**
 * This class is using the Delegate property to implement the Decorator Pattern
 *
 * The structure of this class also follow the "Open Close principle" from SOLID principle
 */
class ServiceApiRxDelegateServiceApiRx(val service: ServiceApiRx) : ServiceApiRx by service {

  override fun getTopTracks(pLimit: Int) =
    service
        .getTopTracks(pLimit)
        .map {
          it.apply {
            /** the rank doesn't come from the BE,
             * it is implicit, the rank match with the index of each element  */
            tracks.list.mapIndexed { index, track -> track.rank = (index + 1) }
          }
        }

}