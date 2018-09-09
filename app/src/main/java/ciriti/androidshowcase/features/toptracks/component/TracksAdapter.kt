package ciriti.androidshowcase.features.toptracks.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ciriti.androidshowcase.core.components.ViewTransitionInfo
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TracksAdapter @Inject constructor()  {

    class ViewHolder (item : View) : RecyclerView.ViewHolder(item) {
        fun bind(track : TrackViewInfo, clickListener: (TrackViewInfo, ViewTransitionInfo) -> Unit) {

        }
    }

}