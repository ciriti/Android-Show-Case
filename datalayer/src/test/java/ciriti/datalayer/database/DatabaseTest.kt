@file:Suppress("IllegalIdentifier")

package ciriti.datalayer.database

import ciriti.datalayer.network.TopTrack
import org.junit.Before
import org.junit.Test
import util.createGsonObj

/**
 * Created by ciriti
 */
class DatabaseTest {

    lateinit var db : IDatabase

    @Before
    fun setup(){
        db = Database()
    }

    @Test
    fun `test subscription to get update`(){
        val collection = "top_tracks.json".createGsonObj<TopTrack>().tracks.track
        db.saveCollection(collection)
        val test = db.getCollection().test()
        test.assertNoErrors()
        test.assertValue { it.size == collection.size }
    }

    @Test
    fun `test subscription to get update on an empty collection`(){
        val test = db.getCollection().test()
        test.assertNoErrors()
        test.assertValue { it.isEmpty() }
    }

    @Test
    fun `test subscription to a track by name`(){
        val collection = "top_tracks.json".createGsonObj<TopTrack>().tracks.track
        db.saveCollection(collection)
        val test = db.getTrackByName(collection[0].name).test()
        test.assertNoErrors()
        test.assertValue { it.name == collection[0].name }
    }

}