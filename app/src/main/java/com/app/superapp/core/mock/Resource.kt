package com.app.superapp.core.mock

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}

suspend fun simulateNetworkDelay() {
    kotlinx.coroutines.delay((300..800).random().toLong())
}
