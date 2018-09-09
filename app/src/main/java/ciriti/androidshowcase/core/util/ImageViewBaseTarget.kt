package ciriti.androidshowcase.core.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.transition.Transition

/**
 * Created by ciriti
 */
class ImageViewBaseTarget(
  var imageView: ImageView?,
  var activity: FragmentActivity?
) : BaseTarget<Drawable>() {
  override fun removeCallback(cb: SizeReadyCallback?) {
    imageView = null
    activity = null
  }

  override fun onResourceReady(
    resource: Drawable,
    transition: Transition<in Drawable>
  ) {
    imageView?.setImageDrawable(resource)
    activity?.supportStartPostponedEnterTransition()
  }

  override fun onLoadFailed(errorDrawable: Drawable?) {
    super.onLoadFailed(errorDrawable)
    activity?.supportStartPostponedEnterTransition()
  }

  override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}