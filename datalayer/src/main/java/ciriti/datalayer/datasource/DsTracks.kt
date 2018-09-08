package ciriti.datalayer.datasource

import androidx.annotation.MainThread
import ciriti.datalayer.database.IDatabase
import ciriti.datalayer.network.ServiceApiRx
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ciriti
 */

interface IDsTracks{

    fun updateTopTracks(limit : Int) : Completable

}

class DsTracks(
        val networAdapter : ServiceApiRx,
        val database : IDatabase,
        // useful for test, you can inject the schedulers
        val workerScheduler : Scheduler = Schedulers.computation(),
        val mainScheduler   : Scheduler = AndroidSchedulers.mainThread()
) : IDsTracks{

    override fun updateTopTracks(limit: Int) : Completable{
        return networAdapter
                .getTopTracks(limit)
                .map {
                    // TODO save on db
                    it
                }
                .toCompletable()
    }
}