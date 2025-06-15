package cz.home.nbaplayers.library.data.infrastructure

fun <T> ResultData<T>.toLoadableData(): LoadableData<T> = when (this) {
    is Data.Success -> this
    is Data.Error -> this
}