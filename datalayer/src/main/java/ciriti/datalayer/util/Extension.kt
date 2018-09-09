package ciriti.datalayer.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ciriti
 */
fun <T> Single<T>.subscribeOnWorkerT() = subscribeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.observeOnAndroidMT() = subscribeOn(Schedulers.computation())
/** Context */
val Context.networkInfo: NetworkInfo? get() =
  (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo