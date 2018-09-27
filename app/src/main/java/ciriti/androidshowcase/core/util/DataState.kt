package ciriti.androidshowcase.core.util

import androidx.annotation.StringRes
import ciriti.androidshowcase.core.components.FlatTrack

/**
 * Created by ciriti
 */

sealed class BaseState

data class DefaultState(val data: List<FlatTrack>) : BaseState()
data class CustomState(val data: List<FlatTrack>) : BaseState()
data class LoadingState(val isLoading: Boolean) : BaseState()
data class ErrorState(@StringRes val errorMessage: Int) : BaseState()
