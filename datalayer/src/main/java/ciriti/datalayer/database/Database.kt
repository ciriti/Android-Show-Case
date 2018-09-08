package ciriti.datalayer.database

import ciriti.datalayer.network.Track
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by ciriti
 */

/**
 * The implementation can be as you wish
 *
 * This is a fake db
 *
 */

interface IDatabase {
    fun saveCollection(tracks : List<Track>)
    fun getCollection() : BehaviorProcessor<List<Track>>
    fun getTrackByName(name : String) : BehaviorProcessor<Track>
}

class Database(
        val listProcessor : BehaviorProcessor<List<Track>> = BehaviorProcessor.create(),
        val trackProcessor : BehaviorProcessor<Track> = BehaviorProcessor.create()
) : IDatabase {

    private var tracks: List<Track> = emptyList()

    init {
        listProcessor.onNext(tracks)
        trackProcessor.onNext(Track())
    }

    override fun saveCollection(tracks: List<Track>) {
        this.tracks = tracks
        listProcessor.onNext(tracks.toList())
    }

    override fun getCollection(): BehaviorProcessor<List<Track>> = listProcessor

    override fun getTrackByName(name: String): BehaviorProcessor<Track> {
        return trackProcessor
                .onNext(tracks.find { it.name == name }?:Track())
                .let { trackProcessor }
    }
}