package ciriti.datalayer.network

import ciriti.datalayer.annotation.MakeItOpenForTest
import ciriti.datalayer.util.minSec
import com.google.gson.annotations.SerializedName

/**
 * Created by ciriti
 */

data class Artist(
  @SerializedName("name")
  val name: String,
  @SerializedName("mbid")
  val mbid: String,
  @SerializedName("url")
  val url: String
)

data class Attr(
  @SerializedName("page")
  val page: String,
  @SerializedName("perPage")
  val perPage: String,
  @SerializedName("totalPages")
  val totalPages: String,
  @SerializedName("total")
  val total: String
)

@MakeItOpenForTest
data class TopTrack(
  @SerializedName("tracks")
  val tracks: Tracks
)

@MakeItOpenForTest
data class Tracks(
  @SerializedName("track")
  val list: List<Track>,
  @SerializedName("attr")
  val attr: Attr
)

data class Track(
  @SerializedName("name")
  val name: String = "",
  @SerializedName("duration")
  val duration: String = "",
  @SerializedName("playcount")
  val playcount: String = "",
  @SerializedName("listeners")
  val listeners: String = "",
  @SerializedName("mbid")
  val mbid: String = "",
  @SerializedName("url")
  val url: String = "",
  @SerializedName("streamable")
  val streamable: Streamable? = null,
  @SerializedName("artist")
  val artist: Artist? = null,
  @SerializedName("image")
  val image: List<Image> = emptyList(),
  val timestamp: String = String.minSec,
  var rank: Int = 0
)

data class Streamable(
  @SerializedName("#text")
  val text: String,
  @SerializedName("fulltrack")
  val fulltrack: String
)

data class Image(

  @SerializedName("#text")
  var text: String? = null,
  @SerializedName("size")
  var size: String? = null

)