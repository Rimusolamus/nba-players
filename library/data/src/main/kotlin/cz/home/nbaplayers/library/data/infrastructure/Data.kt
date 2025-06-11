package cz.home.nbaplayers.library.data.infrastructure

sealed interface LoadableData<out T>

class Data {
    data object Loading : LoadableData<Nothing>

    data class Success<T>(val data: T) : LoadableData<T>

    data class Error(val exception: Throwable) : LoadableData<Nothing>
}