package ciriti.androidshowcase.core.components

import android.view.View
import java.io.Serializable

/**
 * Created by ciriti
 */

data class ViewTransitionInfo(val transitionSharedElement: View)

data class FlatTrack(
  val title: String = "",
  val duration: String = "",
  val playcount: String = "",
  val listeners: String = "",
  val mbid: String = "",
  val url: String = "",
  val artistName: String = "",
  val artistUrl: String = "",
  val imageUrl_S: String = "",
  val imageUrl_M: String = "",
  val imageUrl_L: String = "",
  val imageUrl_XL: String = ""
) : Serializable