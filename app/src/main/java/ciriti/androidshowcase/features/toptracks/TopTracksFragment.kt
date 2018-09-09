package ciriti.androidshowcase.features.toptracks

import androidx.fragment.app.Fragment
import ciriti.androidshowcase.features.toptracks.component.TracksAdapter
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksFragment : Fragment() {

  @Inject lateinit var tracksAdapter: TracksAdapter

}