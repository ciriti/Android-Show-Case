package ciriti.androidshowcase.features.toptracks.component

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.loadFromUrl
import kotlinx.android.synthetic.main.row_track.view.trackWall
import kotlinx.android.synthetic.main.row_track.view.tv_artist
import kotlinx.android.synthetic.main.row_track.view.tv_title

/**
 * Created by ciriti
 */

fun RowTrack.bind(track: FlatTrack) {
  trackWall.loadFromUrl(track.imageUrl_XL)
  tv_artist.text = track.artistName
  tv_title.text = track.title
}

fun RecyclerView.configureCustomVisualization() {
  (layoutManager as GridLayoutManager).spanSizeLookup =
      object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          return 2 - if (position % 3 != 0) 1 else 0
        }
      }
}

fun RecyclerView.configureStandardVisualization() {
  layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
}