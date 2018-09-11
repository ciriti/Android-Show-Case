package ciriti.androidshowcase.features.toptracks.component

import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.loadFromUrl
import kotlinx.android.synthetic.main.row_track.view.trackWall

/**
 * Created by ciriti
 */

fun RowTrack.bind(track: FlatTrack) {
  trackWall.loadFromUrl(track.imageUrl_XL)
}

fun FragmentTracksLayout.bind() {

}