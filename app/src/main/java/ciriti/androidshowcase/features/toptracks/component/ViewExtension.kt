package ciriti.androidshowcase.features.toptracks.component

import android.view.View
import android.widget.TextView
import androidx.core.content.res.TypedArrayUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.loadFromUrl
import kotlinx.android.synthetic.main.row_track.view.trackWall
import kotlinx.android.synthetic.main.row_track.view.tv_artist
import kotlinx.android.synthetic.main.row_track.view.tv_rank
import kotlinx.android.synthetic.main.row_track.view.tv_timestamp
import kotlinx.android.synthetic.main.row_track.view.tv_title

/**
 * Created by ciriti
 */

fun RowTrack.bind(track: FlatTrack) {
  trackWall.loadFromUrl(track.imageUrl_XL)
  tv_artist.text = track.artistName
  tv_title.text = track.title
  tv_timestamp.text = String.format(context.getString(R.string.last_update), track.time)
  tv_rank.hideIfValueIsEmpty(track.rank)
}

fun RecyclerView.configureStandardVisualization() {
  layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
}

fun TextView.hideIfValueIsEmpty(value : String){
  visibility = when(value.isEmpty()){
    true -> View.GONE
    false -> View.VISIBLE
  }
  text = value
}