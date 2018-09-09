package util

import com.google.common.io.ByteSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.InputStream


/**
 * Created by ciriti
 */

/**
 * Parse file.json and return list of objects
 */
inline fun<reified K> String.createListObjByJsonFile() : K{
    val typeToken = object : TypeToken<K>() {}.type
    val jsonString = this.jsonFile2String()
    var objs : K = Gson().fromJson<K>(jsonString, typeToken)
    return objs
}

/**
 * Receive file.json and return the content as string
 */
fun String.jsonFile2String() : String{
    val inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(this)
    val byteSource = object : ByteSource() {
        override fun openStream(): InputStream {
            return inputStream
        }
    }

    return byteSource.asCharSource(Charsets.UTF_8).read()
}

/**
 * Receive a file.json and create a reified Object
 */
inline fun <reified T> String.createGsonObj() : T{
    val jsonString = this.jsonFile2String()
    return Gson().fromJson(jsonString, T::class.java)
}

/**
 * Utility for test
 */
inline fun <reified T> retrofit2.Retrofit.Builder.createAdapter(url : String) : T {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val builder =  this
            .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .baseUrl(url)
            .client(client)
            .build()
    return builder.create(T::class.java)
}