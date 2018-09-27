package ciriti.androidshowcase.core.components

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by ciriti
 */
abstract class BaseViewModel : ViewModel() {

  val disposables = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }

}