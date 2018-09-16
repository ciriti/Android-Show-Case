package ciriti.androidshowcase.core.components

import android.content.Context
import ciriti.androidshowcase.core.createListObjByJsonFile
import ciriti.androidshowcase.core.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.Track
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

/**
 * This is an example of delegation in kotlin.
 * To
 */

class DBDelegate(
  val context: Context,
  private val db: IDatabase
) : IDatabase by db {

  override fun saveCollection(tracks: List<Track>) {
    db.saveCollection(tracks)
    val listString = Gson().toJson(tracks)
    context.preferences.edit()
        .putString("cache", listString)
        .apply()
  }

  override fun getCollection(): BehaviorProcessor<List<Track>> {
    val chachedList: String = context.preferences.getString("cache", "")!!
    if (chachedList.isNotEmpty()) {
      val tracks = chachedList.createListObjByJsonFile<List<Track>>()
      db.saveCollection(tracks)
    }
    return db.getCollection()
  }

}