package cz.home.nbaplayers.library.data.infrastructure

sealed interface LoadableData<out T>

sealed interface ResultData<out T>

class Data {
    data object Loading : LoadableData<Nothing>

    data class Success<out T>(val value: T) : LoadableData<T>, ResultData<T>

    data class Error(
        val exception: Throwable,
        val networkRequest: NetworkRequest? = null
    ) : LoadableData<Nothing>, ResultData<Nothing> {
        data class NetworkRequest(
            val url: String
        )
    }
}