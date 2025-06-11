package cz.home.nbaplayers.library.networking.infrastructure

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(private val tokenProvider: () -> String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = tokenProvider()

        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.header("Authorization", token)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}