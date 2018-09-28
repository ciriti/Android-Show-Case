package ciriti.androidshowcase.core.components

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by Carmelo Iriti
 */
class FragmentLifecycleCallbacks_ : FragmentManager.FragmentLifecycleCallbacks() {

    private var onFragmentAttached_ : ((fm: FragmentManager, f: Fragment, context: Context) -> Unit)? = null

    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        super.onFragmentAttached(fm, f, context)
        onFragmentAttached_?.invoke(fm, f, context)
    }

    fun onFragmentAttached_(func : (fm: FragmentManager, f: Fragment, context: Context) -> Unit) {
        onFragmentAttached_ = func
    }
}

fun FragmentManager.registerFragmentLifecycleCallbacks (init : FragmentLifecycleCallbacks_.() -> Unit) : FragmentManager.FragmentLifecycleCallbacks {
    val listener = FragmentLifecycleCallbacks_()
    listener.init()
    registerFragmentLifecycleCallbacks(listener, true)
    return listener
}
