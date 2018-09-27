package ciriti.androidshowcase.features.toptracks

import androidx.lifecycle.MutableLiveData
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.handleException
import ciriti.androidshowcase.core.observeOnAndroidMT
import ciriti.androidshowcase.core.plusAssign
import ciriti.androidshowcase.core.subscribeOnWorkerT
import ciriti.androidshowcase.core.util.BaseState
import ciriti.androidshowcase.core.components.BaseViewModel
import ciriti.androidshowcase.core.util.DefaultState
import ciriti.androidshowcase.core.util.ErrorState
import ciriti.androidshowcase.core.util.LoadingState
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksViewModel @Inject constructor(
  private val topTracksUseCase: ITopTracksUseCase
) : BaseViewModel() {

  val liveData by lazy { MutableLiveData<BaseState>() }

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

  fun loadTracks(limit: Int = 50) {
    disposables += topTracksUseCase
        .updateTopTracks(limit)
        .subscribeOnWorkerT()
        .observeOnAndroidMT()
        .doOnSubscribe { liveData.value = LoadingState(true) }
        .subscribe(this::onComplete, this::onError)
  }

  private fun onSuccess(res: List<FlatTrack>) {
    liveData.value = DefaultState(res)
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
