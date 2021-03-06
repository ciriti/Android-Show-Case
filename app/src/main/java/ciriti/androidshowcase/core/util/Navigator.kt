package ciriti.androidshowcase.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import ciriti.androidshowcase.core.components.FlatTrack
import ciriti.androidshowcase.core.components.ViewTransitionInfo
import ciriti.androidshowcase.features.toptracks.ActivityTopTracks
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ciriti
 */

@Singleton
class Navigator @Inject constructor(private val session: ISession) {

  private fun showLogin(context: Context) = context.startActivity(
      null
      /** TODO */
  )

  fun showMain(context: Context) {
    when (session.isSessionValid()) {
      true -> showTopTracks(context)
      false -> showLogin(context)
    }
  }

  private fun showTopTracks(context: Context) = context.invokeActivity<ActivityTopTracks>()

  fun showTrackDetails(
    activity: FragmentActivity,
    trackInfo: FlatTrack,
    transitionInfo: ViewTransitionInfo
  ) {
    val intent = Intent(activity, activity::class.java)
    intent.putExtra("ciriti.androidshowcase.INTENT_PARAM_TRACK", trackInfo)

    val sharedView = transitionInfo.transitionSharedElement as ImageView
    val activityOptions = ActivityOptionsCompat
        .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
    activity.startActivity(intent, activityOptions.toBundle())
  }

  private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
  private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

  fun openYoutubeVideo(
    context: Context,
    videoUrl: String
  ) {
    try {
      context.startActivity(createYoutubeIntent(videoUrl))
    } catch (ex: ActivityNotFoundException) {
      context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
    }
  }

  private fun createYoutubeIntent(videoUrl: String): Intent {
    val videoId = when {
      videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
      videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
      else -> videoUrl
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
    intent.putExtra("force_fullscreen", true)

    if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M)
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    return intent
  }

}