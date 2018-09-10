package ciriti.androidshowcase.features

import ciriti.androidshowcase.core.components.FlatTrack

/**
 * Created by ciriti
 */

sealed class CurrencyState(val list: List<FlatTrack> = emptyList())

data class DefaultState(val data: List<FlatTrack>) : CurrencyState(data)
data class NormalState(val data: List<FlatTrack>) : CurrencyState(data)
data class LoadingState(val isLoading : Boolean) : CurrencyState(emptyList())
data class ErrorState(val errorMessage: String) : CurrencyState(emptyList())

