@file:Suppress("IllegalIdentifier")

package ciriti.androidshowcase

import android.util.Log
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ciriti.datalayer.network.TopTrack
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Test
import org.junit.runner.RunWith
import util.createGsonObj
import com.google.firebase.firestore.CollectionReference
import com.google.gson.Gson


/**
 * Created by ciriti
 */
@RunWith(AndroidJUnit4::class)
class FirestoreTest {

    @Test
    fun fireStoreInsertTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val topTracks = "top_tracks.json".createGsonObj<TopTrack>()

        FirebaseApp.initializeApp(appContext)

        val collection = FirebaseFirestore.getInstance().collection("top_track")

        val map = mapOf("saved_top_tracks" to Gson().toJson(topTracks).toString())

        collection
                .add(map)
                .addOnSuccessListener {
                    println("success")
                }
                .addOnFailureListener { println("failure") }

        collection
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for(doc in task.result){
                            Log.d("mytag", doc.id + " => " + doc.data)
                        }

                    } else {
                        Log.w("mytag", "Error getting documents.", task.getException())
                    }
                }




    }
    @Test
    fun fireStoreGetTest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val topTracks = "top_tracks.json".createGsonObj<TopTrack>()

        FirebaseApp.initializeApp(appContext)

        val collection = FirebaseFirestore.getInstance().collection("top_track")

        val map = mapOf("saved_top_tracks" to Gson().toJson(topTracks).toString())

        collection
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for(doc in task.result){
                            Log.d("mytag", " => " + doc.data)
                        }

                    } else {
                        Log.w("mytag", "Error getting documents.", task.getException())
                    }
                }

        println()




    }

}