package ciriti.datalayer.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by ciriti
 */
fun <T> Single<T>.subscribeOnWorkerT() = subscribeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.observeOnAndroidMT() = subscribeOn(Schedulers.computation())
/** Context */
val Context.networkInfo: NetworkInfo? get() =
  (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo


/**
 * Timestamp
 */

val Any.minSec: String
  get() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())

