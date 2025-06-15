package cz.home.nbaplayers.library.networking.data

class UnsuccessfulResponseException(
    val httpStatusCode: Int,
    message: String
) : RuntimeException(message)