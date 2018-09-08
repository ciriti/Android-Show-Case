package ciriti.datalayer.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ciriti
 */
interface ServiceApiRx {

    @GET("$URL/?api_key=$API_KEY&method=chart.gettoptracks&format=json&limit=?")
    fun getTopTracks(@Query("limit") pLimit : Int): Single<List<TopTrack>>

    @GET("$URL/?api_key=$API_KEY&method=chart.gettopartists&format=json&limit=?")
    fun getTopArtists(@Query("limit") pLimit : Int): Single<List<TopTrack>>

}