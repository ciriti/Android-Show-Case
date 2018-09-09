package ciriti.androidshowcase.features

import ciriti.androidshowcase.core.components.FlatTrack

/**
 * Created by ciriti
 */

sealed class CurrencyState {
    abstract val data: List<FlatTrack>
}

data class DefaultState(override val data: List<FlatTrack>) : CurrencyState()
data class NormalState(override val data: List<FlatTrack>) : CurrencyState()
data class LoadingState(override val data: List<FlatTrack>) : CurrencyState()
data class ErrorState(val errorMessage: String, override val data: List<FlatTrack> = emptyList<FlatTrack>()) : CurrencyState()

