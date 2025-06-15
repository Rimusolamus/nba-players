package cz.home.nbaplayers.library.networking.infrastructure

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(val provideToken: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        // log the request details if needed
        val token = provideToken()

        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.header("Authorization", token)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}