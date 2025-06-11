package cz.home.nbaplayers.library.networking.di

import cz.home.nbaplayers.library.networking.infrastructure.ApiService
import cz.home.nbaplayers.library.networking.infrastructure.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private fun provideToken(): String {
    return "39f00294-ac83-47a8-9ce4-18b3edc84a19"
}

private fun provideBaseUrl(): String {
    return "https://api.balldontlie.io"
}

val networkingModule = module {
    single {
        // inject a service here that retrieves the token
        AuthInterceptor { provideToken() }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<ApiService> {
         Retrofit.Builder()
             // inject a service here that retrieves the base URL
             .baseUrl(provideBaseUrl())
             .client(get<OkHttpClient>())
             .addConverterFactory(MoshiConverterFactory.create())
             .build()
             .create(ApiService::class.java)
    }
}