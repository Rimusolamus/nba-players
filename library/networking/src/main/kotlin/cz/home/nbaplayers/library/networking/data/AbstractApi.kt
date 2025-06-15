package cz.home.nbaplayers.library.networking.data

import cz.home.nbaplayers.library.data.infrastructure.Data
import cz.home.nbaplayers.library.data.infrastructure.LoadableData
import cz.home.nbaplayers.library.data.infrastructure.ResultData
import cz.home.nbaplayers.library.data.infrastructure.toLoadableData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AbstractApi {
    fun <DTO, MODEL> request(
        fetchData: suspend () -> Response<DTO>,
        parseDto: DTO.() -> MODEL
    ): Flow<LoadableData<MODEL>> = flow {
        emit(Data.Loading)

        emit(requestSynchronous(fetchData, parseDto).toLoadableData())
    }

    private suspend fun <DTO, MODEL> requestSynchronous(
        doRequest: suspend () -> Response<DTO>,
        parseDto: (DTO.() -> MODEL),
    ): ResultData<MODEL> {
        val response = when (val result = executeRequest(doRequest)) {
            is Data.Success -> result.value
            is Data.Error -> return result
        }
        return when {
            response.isSuccessful -> processSuccessfulResponse(response, parseDto)
            else -> processUnsuccessfulResponse<DTO, MODEL>(response) as Data.Error
        }
    }

    private suspend fun <DTO> executeRequest(fetchData: suspend () -> Response<DTO>): ResultData<Response<DTO>> = try {
        Data.Success(withContext(Dispatchers.IO) { fetchData() })
    } catch (e: Exception) {
        when (e) {
            is SocketTimeoutException -> Data.Error(
                NetworkErrorException(ERROR_MESSAGE_UNABLE_TO_PROCEED_REQUEST, e),
            )
            is UnknownHostException,
            is ConnectException,
            is IOException -> Data.Error(
                NetworkErrorException(
                    ERROR_MESSAGE_NO_INTERNET, e)
            )
            else -> Data.Error(NetworkErrorException(ERROR_MESSAGE_UNABLE_TO_PROCEED_REQUEST, e))
        }
    }

    private suspend fun <DTO, MODEL> processSuccessfulResponse(response: Response<DTO>, parseDto: DTO.() -> MODEL): ResultData<MODEL> =
        withContext(Dispatchers.IO) {
            response.body()?.let { responseBody ->
                try {
                    responseBody.parseDto().let { model ->
                        Data.Success(model)
                    }
                } catch (e: Exception) {
                    Data.Error(ResponseParseException(
                            "Unable to parse response body of API request ${response.raw().request.method} " +
                                    "${response.raw().request.url} to model class.",
                            e
                        ),
                        networkRequest = Data.Error.NetworkRequest(response.raw().request.url.toString())
                    )
                }
            } ?: Data.Error(
                RuntimeException(
                    "API response of ${response.raw().request.method} " +
                            "${response.raw().request.url} doesn't contain any body"
                ),
                networkRequest = Data.Error.NetworkRequest(response.raw().request.url.toString()),
            )
        }

    private suspend fun <DTO, MODEL> processUnsuccessfulResponse(
        response: Response<DTO>,
    ): ResultData<MODEL> = withContext(Dispatchers.IO) {
        when (response.code()) {
            HttpURLConnection.HTTP_CLIENT_TIMEOUT, HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> Data.Error(
                NetworkErrorException(
                    ERROR_MESSAGE_UNABLE_TO_PROCEED_REQUEST,
                    UnsuccessfulResponseException(
                        response.code(), "API request ${response.raw().request.method} " +
                                "${response.raw().request.url} finished with unsuccessful http status code ${response.code()}"
                    )
                ),
                networkRequest = Data.Error.NetworkRequest(response.raw().request.url.toString()),
            )
            else -> Data.Error(
                UnsuccessfulResponseException(
                    response.code(),
                    "API request ${response.raw().request.method} " +
                            "${response.raw().request.url} finished with unsuccessful http status code ${response.code()}",
                ),
                networkRequest = Data.Error.NetworkRequest(response.raw().request.url.toString()),
            )
        }
    }

    companion object {
        private const val ERROR_MESSAGE_UNABLE_TO_PROCEED_REQUEST = "Unable to proceed request."
        private const val ERROR_MESSAGE_NO_INTERNET = "No internet. Check your connection and try again."
    }
}