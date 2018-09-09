package ciriti.androidshowcase.core

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import ciriti.androidshowcase.features.toptracks.TopTracksActivity
import ciriti.androidshowcase.features.toptracks.widget.TrackViewInfo

/**
 * Created by ciriti
 */
class Navigator(val session : Session) {

    private fun showLogin(context: Context) = context.startActivity(null /** TODO */)

    fun showMain(context: Context) {
        when (session.isSessionValid()) {
            true -> showTopTracks(context)
            false -> showLogin(context)
        }
    }

    private fun showTopTracks(context: Context) = context.invokeActivity<TopTracksActivity>()

    fun showTrackDetails(activity: FragmentActivity, trackInfo: TrackViewInfo, transitionSharedElement: View) {
        val intent = Intent(activity, activity::class.java)
        intent.putExtra("ciriti.androidshowcase.INTENT_PARAM_TRACK", trackInfo)

        val sharedView = transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openYoutubeVideo(context: Context, videoUrl: String) {
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