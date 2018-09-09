package ciriti.datalayer.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ciriti
 */
interface ServiceApiRx {

  @GET("$URL/?api_key=$API_KEY&method=chart.gettoptracks&format=json&?")
  fun getTopTracks(@Query("limit") pLimit: Int): Single<TopTrack>

  // TODO getTopArtists
  @GET("$URL/?api_key=$API_KEY&method=chart.gettopartists&format=json&?")
  fun getTopArtists(@Query("limit") pLimit: Int): Single<List<TopTrack>>

}