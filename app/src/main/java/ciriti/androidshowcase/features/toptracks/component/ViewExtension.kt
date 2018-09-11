package ciriti.androidshowcase.features.toptracks.component

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.loadFromUrl
import kotlinx.android.synthetic.main.row_track.view.trackWall

/**
 * Created by ciriti
 */

fun RowTrack.bind(track: FlatTrack) {
  trackWall.loadFromUrl(track.imageUrl_XL)
}

fun GridLayoutManager.configureCustomVisualization() {
  spanSizeLookup =
      object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          val span = 2 - if (position % 3 != 0) 1 else 0
          Log.i("", "span[$span] pos[$position]")
          return span
        }
      }
}