package ciriti.androidshowcase

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by ciriti
 */
class TrackApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    FirebaseApp.initializeApp(this)

    val db = FirebaseFirestore.getInstance()

//        db
//                .collection("top_tracks")
//                .add(Pair("test", "test"))
//                .addOnSuccessListener {
//                    println(it)
//                }

    val a = db.collection("top_tracks")
        .get()
        .addOnCompleteListener {
          if (it.isSuccessful()) {
            for (document in it.getResult()) {
              Log.d("mytag", document.getId() + " => " + document.getData());
            }
          } else {
            Log.w("mytag", "Error getting documents.", it.getException());
          }
        }

    println("test")
  }
}