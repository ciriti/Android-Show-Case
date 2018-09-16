package ciriti.androidshowcase.core.components

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ciriti.androidshowcase.core.cachedTracks
import ciriti.androidshowcase.core.preferences
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.TopTrack
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import util.createGsonObj

/**
 * Created by ciriti
 */
@RunWith(AndroidJUnit4::class)
class DBDelegateTest {

  lateinit var db: IDatabase
  lateinit var context: Context
  lateinit var dBDelegate: DBDelegate

  @Before
  fun setup() {
    db = Mockito.mock(IDatabase::class.java)
    context = InstrumentationRegistry.getTargetContext()
    dBDelegate = DBDelegate(context, db)
  }

  @Test
  fun storeDataTest() {
    /** data for test */
    val serverResp = "top_tracks.json".createGsonObj<TopTrack>()
    val tracksList = serverResp.tracks.list
    val tracks2Cache: String = Gson().toJson(tracksList)

    /** obj under test */
    dBDelegate.saveCollection(tracksList)

    /** verify that the mock method id called exactly one time */
    Mockito.verify(db, Mockito.times(1)).saveCollection(tracksList)

    /** verify that the cached value is correct */
    Assert.assertEquals(tracks2Cache, context.preferences.cachedTracks)
  }

  @Test
  fun getCollectionTest() {
    /** data for test */
    val serverResp = "top_tracks.json".createGsonObj<TopTrack>()
    val tracksList = serverResp.tracks.list
    val tracks2Cache: String = Gson().toJson(tracksList)
    context.preferences.cachedTracks = tracks2Cache

    /** obj under test */
    dBDelegate.getCollection()

    /** verify that the mock method id called exactly one time */
    Mockito.verify(db, Mockito.times(1)).saveCollection(tracksList)
    Mockito.verify(db, Mockito.times(1)).getCollection()

  }
}