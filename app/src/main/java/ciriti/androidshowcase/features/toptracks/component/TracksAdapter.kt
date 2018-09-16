package ciriti.androidshowcase.features.toptracks.component

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.components.ViewTransitionInfo
import ciriti.androidshowcase.core.inflate
import kotlinx.android.synthetic.main.row_track.view.trackWall
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by ciriti
 */
class TracksAdapter @Inject constructor() : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {

  /**
   * Observable property - every time a change occur the listener is called
   */
  var collection: List<FlatTrack> by Delegates.observable(emptyList()) { _, _, _ ->
    notifyDataSetChanged()
  }

  /**
   * Listener invoked if the collection change
   */
  var clickListener: (FlatTrack, ViewTransitionInfo) -> Unit = { _, _ -> }

  /**
   * Create a view instance for the recicleview
   */
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = ViewHolder(parent.inflate(R.layout.row_track))

  override fun getItemCount() = collection.size

  /**
   * Bind the java bean with the related view
   */
  override fun onBindViewHolder(
    holder: ViewHolder,
    position: Int
  ) = holder.bind(collection[position], position, clickListener)

  /**
   * Holder class
   */
  class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    fun bind(
      track: FlatTrack,
      position : Int,
      clickListener: (FlatTrack, ViewTransitionInfo) -> Unit
    ) {
      if (itemView is RowTrack) {
        itemView.bind(track, (position + 1 ).toString())
      }
      this.itemView.setOnClickListener {
        clickListener(
            track, ViewTransitionInfo(itemView.trackWall)
        )
      }
    }
  }
}