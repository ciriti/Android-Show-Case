package util

import org.mockito.Mockito

/**
 * Created by ciriti
 */
interface BaseTest {

    fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }
    private fun <T> uninitialized(): T = null as T

}