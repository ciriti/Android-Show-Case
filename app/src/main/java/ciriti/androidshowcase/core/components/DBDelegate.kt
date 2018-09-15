package ciriti.androidshowcase.core.components

import android.content.Context
import ciriti.androidshowcase.core.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.Track
import com.google.gson.Gson
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

const val filename = "dbFile"

/**
 * This is an e
 */
class DBDelegate(
  val context: Context,
  private val db: IDatabase
) : IDatabase by db {

  override fun saveCollection(tracks: List<Track>) {
    db.saveCollection(tracks)
    val listString = Gson().toJson(tracks)
    context.preferences.edit().putString("cache", listString).apply()
  }

  override fun getCollection(): BehaviorProcessor<List<Track>> {
    val s : String = context
        .preferences
        .getString("cache", "")!!
    if(s.isNotEmpty()){
      println()
    }
    return db.getCollection()
  }

}