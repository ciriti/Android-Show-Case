package ciriti.androidshowcase.features.toptracks

import androidx.lifecycle.MutableLiveData
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.observeOnAndroidMT
import ciriti.androidshowcase.core.plusAssign
import ciriti.androidshowcase.core.subscribeOnWorkerT
import ciriti.androidshowcase.features.BaseViewModel
import ciriti.androidshowcase.features.CurrencyState
import ciriti.androidshowcase.features.DefaultState
import ciriti.androidshowcase.features.ErrorState
import ciriti.androidshowcase.features.NormalState
import ciriti.datalayer.exception.NoNetworkException
import java.security.InvalidParameterException
import javax.inject.Inject

/**
 * Created by ciriti
 */
class TopTracksViewModel @Inject constructor(
  val topTracksUseCase: TopTracksUseCase
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
        .doOnSubscribe { println("startloading") }
        .subscribe(this::onComplete, this::onError)
  }

  private fun onSuccess(res: List<FlatTrack>) {
    liveData.value = NormalState(res)
  }

  private fun onComplete() {
    println("Complete")
  }

  private fun onError(error: Throwable) {
    /**
     *  manage the exception
     */
    when (error) {
      is InvalidParameterException -> liveData.value =
          ErrorState("InvalidParameterException", emptyList())
      is NoNetworkException -> liveData.value = ErrorState("Offline mod active")
      else -> liveData.value = ErrorState("Generic exception")
    }

  }

}
