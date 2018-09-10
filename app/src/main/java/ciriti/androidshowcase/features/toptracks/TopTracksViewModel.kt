package ciriti.androidshowcase.features.toptracks

import androidx.lifecycle.MutableLiveData
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.handleException
import ciriti.androidshowcase.core.observeOnAndroidMT
import ciriti.androidshowcase.core.plusAssign
import ciriti.androidshowcase.core.subscribeOnWorkerT
import ciriti.androidshowcase.features.BaseViewModel
import ciriti.androidshowcase.features.CurrencyState
import ciriti.androidshowcase.features.DefaultState
import ciriti.androidshowcase.features.ErrorState
import ciriti.androidshowcase.features.LoadingState
import ciriti.androidshowcase.features.NormalState
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksViewModel @Inject constructor(
  private val topTracksUseCase: TopTracksUseCase
) : BaseViewModel() {

  val liveData by lazy { MutableLiveData<CurrencyState>() }

  init {
    liveData.value = DefaultState(emptyList())
  }

  fun clear() {
    liveData.value = DefaultState(emptyList())
  }

  fun observeTopTracks() {
    disposables += topTracksUseCase
        .observeTopTrackList()
        .subscribeOnWorkerT()
        .observeOnAndroidMT()
        .subscribe(this::onSuccess, this::onError)
  }

  fun loadTracks(limit: Int) {
    topTracksUseCase
        .updateTopTracks(limit)
        .subscribeOnWorkerT()
        .observeOnAndroidMT()
        .doOnSubscribe { liveData.value = LoadingState(false) }
        .subscribe(this::onComplete, this::onError)
  }

  private fun onSuccess(res: List<FlatTrack>) {
    liveData.value = NormalState(res)
  }

  private fun onComplete() {
    liveData.value = LoadingState(false)
  }

  /**
   *  manage the exception
   */
  private fun onError(error: Throwable) {
    liveData.value = ErrorState(handleException(error))
  }

}
