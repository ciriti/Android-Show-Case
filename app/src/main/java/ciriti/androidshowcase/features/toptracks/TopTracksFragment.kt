package ciriti.androidshowcase.features.toptracks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.components.BaseFragment
import ciriti.androidshowcase.core.invisible
import ciriti.androidshowcase.core.setColorsSwipeRefresh
import ciriti.androidshowcase.core.util.Navigator
import ciriti.androidshowcase.core.visible
import ciriti.androidshowcase.features.CustomState
import ciriti.androidshowcase.features.DefaultState
import ciriti.androidshowcase.features.ErrorState
import ciriti.androidshowcase.features.LoadingState
import ciriti.androidshowcase.features.toptracks.component.TracksAdapter
import ciriti.androidshowcase.features.toptracks.component.configureStandardVisualization
import kotlinx.android.synthetic.main.fragment_top_track.emptyView
import kotlinx.android.synthetic.main.fragment_top_track.swiperefresh
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
    setColorsSwipeRefresh(R.color.swipe_green, R.color.swipe_yellow, R.color.swipe_red)
    init()
    loadTracksList()
  }

  fun init() {
    /** Ext. used to configure the layout of the recyclerview */
    trackList.configureStandardVisualization()
    /** enable the viewmodel to receive update from the DB */
    topTracksViewModel.observeTopTracks()
    trackList.adapter = tracksAdapter
    /** click listener for recyclerview */
    tracksAdapter.clickListener = { track, navigationExtras ->
      //      navigator.showTrackDetails(activity!!, list, navigationExtras)
    }
    /** connection channel with viewmodel */
    topTracksViewModel
        .liveData
        .observe(this, Observer {
          when (it) {
            is CustomState -> {
              /** future state */
            }
            is ErrorState -> errorHandler(it.errorMessage)
            is LoadingState -> progressState(it)
            is DefaultState -> showData(it)
          }
        })
  }

  private fun loadTracksList() {
    showProgress()
    topTracksViewModel.loadTracks()
    swiperefresh.setOnRefreshListener { topTracksViewModel.loadTracks() }
  }

  private fun showData(state: DefaultState) {
    tracksAdapter.collection = state.data
    checkVisibility()
  }

  private fun progressState(state: LoadingState) = when (state.isLoading) {
    true -> showProgress()
    false -> hideProgress()
  }

  private fun errorHandler(@StringRes message: Int) {
    checkVisibility()
    hideProgress()
    notifyWithAction(message, R.string.action_refresh, ::loadTracksList)
  }

  fun checkVisibility() {
    tracksAdapter.apply {
      if (collection.isEmpty()) {
        trackList.invisible()
        emptyView.visible()
      } else {
        trackList.visible()
        emptyView.invisible()
      }
    }
  }

}
