package ciriti.androidshowcase.features.toptracks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.components.BaseFragment
import ciriti.androidshowcase.core.invisible
import ciriti.androidshowcase.core.util.Navigator
import ciriti.androidshowcase.core.visible
import ciriti.androidshowcase.features.toptracks.component.TracksAdapter
import kotlinx.android.synthetic.main.fragment_top_track.button
import kotlinx.android.synthetic.main.fragment_top_track.emptyView
import kotlinx.android.synthetic.main.fragment_top_track.trackList
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksFragment : BaseFragment() {

  @Inject lateinit var tracksAdapter: TracksAdapter
  @Inject lateinit var navigator: Navigator
  lateinit var topTracksViewModel: TopTracksViewModel

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    topTracksViewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(TopTracksViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_top_track, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    init()
    loadTracksList()
    button.setOnClickListener { topTracksViewModel.loadTracks(10) }
  }

  fun init() {
    trackList.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    trackList.adapter = tracksAdapter
    tracksAdapter.clickListener = { movie, navigationExtras ->
      navigator.showTrackDetails(activity!!, movie, navigationExtras)
    }
    topTracksViewModel.observeTopTracks()
  }

  private fun loadTracksList() {
    emptyView.invisible()
    trackList.visible()
    showProgress()

    topTracksViewModel
        .liveData
        .observe(this, Observer {
          println(it)
        })

//    moviesViewModel.loadMovies()
  }

}