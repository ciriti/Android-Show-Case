package ciriti.androidshowcase.core.components

import android.content.Context
import ciriti.androidshowcase.core.util.cachedTracks
import ciriti.androidshowcase.core.util.createListObjByJsonFile
import ciriti.androidshowcase.core.util.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.Track
import com.google.gson.Gson
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject

/**
 * Created by ciriti
 */

/**
 * This class is using the Delegate property to implement the Decorator Pattern.
 *
 * The structure of it follow the "Open Close principle" from SOLID principle
 */

class DBDelegate @Inject constructor(
  val context: Context,
  private val db: IDatabase
) : IDatabase by db { // Delegate

  /**
   * I'm decorating saveCollection method using SharedPreferences to cache the data
   */
  override fun saveCollection(tracks: List<Track>) {
    db.saveCollection(tracks)
    val listString = Gson().toJson(tracks)
    context.preferences.cachedTracks = listString
  }

  /**
   * I'm decorating getCollection method using SharedPreferences to get from preferences the data
   */
  override fun getCollection(): BehaviorProcessor<List<Track>> {
    val cachedList: String = context.preferences.cachedTracks?:""
    if (cachedList.isNotEmpty()) {
      val tracks = cachedList.createListObjByJsonFile<List<Track>>()
      db.saveCollection(tracks)
    }
    return db.getCollection()
  }

}