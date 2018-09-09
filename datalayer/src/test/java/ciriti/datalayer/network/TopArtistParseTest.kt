package ciriti.datalayer.network

import org.junit.Assert
import org.junit.Test
import util.createGsonObj

/**
 * Created by ciriti
 */
class TopArtistParseTest {

    @Test
    fun topTracksFileParse(){
        val list = "top_tracks.json".createGsonObj<TopTrack>()
        Assert.assertNotNull(list.tracks)
        Assert.assertFalse(list.tracks.track.isEmpty())
    }

    @Test
    fun topArtistsFileParse(){
        // TODO
    }

}