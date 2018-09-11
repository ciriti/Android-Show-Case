package ciriti.androidshowcase.features.toptracks.component

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by ciriti
 */

class RowTrack @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr)

class FragmentTracksLayout @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr)
