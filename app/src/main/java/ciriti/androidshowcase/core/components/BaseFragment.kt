package ciriti.androidshowcase.core.components

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ciriti.androidshowcase.R
import ciriti.androidshowcase.core.appContext
import ciriti.androidshowcase.core.viewContainer
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.toolbar.progress
import javax.inject.Inject

/**
 * Created by ciriti
 */
abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {

  @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }

  open fun onBackPressed() {}

  internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

  internal fun showProgress() = progressStatus(View.VISIBLE)

  internal fun hideProgress() = progressStatus(View.GONE)

  private fun progressStatus(viewStatus: Int) =
    with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

  internal fun notify(@StringRes message: Int) =
    Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

  internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
    val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction(actionText) { _ -> action.invoke() }
    snackBar.setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
    snackBar.show()
  }
}