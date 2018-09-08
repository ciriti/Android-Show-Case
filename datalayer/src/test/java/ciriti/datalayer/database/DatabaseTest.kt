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
    fun getCollection(){
        val collection = "top_tracks.json".createGsonObj<TopTrack>().tracks.track
        db.saveCollection(collection)
        val test = db.getCollection().test()
        test.assertNoErrors()
        test.assertValue { it.size == collection.size }
    }

    @Test
    fun getCollectionEmpty(){
        val test = db.getCollection().test()
        test.assertNoErrors()
        test.assertValue { it.isEmpty() }
    }

    @Test
    fun getTrackByName(){
        val collection = "top_tracks.json".createGsonObj<TopTrack>().tracks.track
        db.saveCollection(collection)
        val test = db.getTrackByName(collection[0].name).test()
        test.assertNoErrors()
        test.assertValue { it.name == collection[0].name }
    }

}