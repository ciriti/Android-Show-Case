package ciriti.androidshowcase.core.components

import android.content.Context
import ciriti.androidshowcase.core.createListObjByJsonFile
import ciriti.androidshowcase.core.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.Track
import com.google.gson.Gson
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

/**
 * This class is using the Delegate property to implement the Decorator Pattern.
 *
 * The structure of it follow the "Open Close principle" from SOLID principle
 */

class DBDelegate(
  val context: Context,
  private val db: IDatabase
) : IDatabase by db { // Delegate

  private val storeKey = "cache"

  /**
   * I'm decorating saveCollection method using SharedPreferences to cache the data
   */
  override fun saveCollection(tracks: List<Track>) {
    db.saveCollection(tracks)
    val listString = Gson().toJson(tracks)
    context.preferences.edit()
        .putString(storeKey, listString)
        .apply()
  }

  /**
   * I'm decorating getCollection method using SharedPreferences to get from preferences the data
   */
  override fun getCollection(): BehaviorProcessor<List<Track>> {
    val cachedList: String = context.preferences.getString(storeKey, "")!!
    if (cachedList.isNotEmpty()) {
      val tracks = cachedList.createListObjByJsonFile<List<Track>>()
      db.saveCollection(tracks)
    }
    return db.getCollection()
  }

}