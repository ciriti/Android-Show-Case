package ciriti.androidshowcase.core.components

import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.util.appContext
import ciriti.androidshowcase.core.util.viewContainer
import ciriti.androidshowcase.di.Injectable
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_top_track.swiperefresh
import javax.inject.Inject

/**
 * Created by ciriti
 */
abstract class BaseFragment : Fragment() , Injectable{

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  fun showProgress() {
    swiperefresh.isRefreshing = true
  }

  fun hideProgress() {
    swiperefresh.isRefreshing = false
  }

  fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
    val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction(actionText) { _ -> action() }
    snackBar.setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
    snackBar.show()
    view?.postDelayed({ snackBar.dismiss() }, 5000)
  }
}