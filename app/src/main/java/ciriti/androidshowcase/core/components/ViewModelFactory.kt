package ciriti.androidshowcase.core.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by ciriti
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
  private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    val creator = creators[modelClass]

    try {
      return creator?.get() as? T ?: throw IllegalArgumentException("Unknown ViewModel class")
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}