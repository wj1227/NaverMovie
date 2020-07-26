package com.jay.navermovie.module

import com.jay.navermovie.api.NaverApi
import com.jay.navermovie.api.NaverApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule: Module = module {

    single {
        Interceptor { chain ->
            with(chain) {
                val request = request().newBuilder()
                    .addHeader("X-Naver-Client-Id", "4htz3Due_zl0kEOqf1yV")
                    .addHeader("X-Naver-Client-Secret", "ylIahW3W7K")
                    .build()
                    proceed(request)
            }
        }
    }

    single<OkHttpClient> { OkHttpClient.Builder()
        .run {
            addInterceptor(get<Interceptor>())
                .build()
        }
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<NaverApi> { get<Retrofit>().create(NaverApi::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(NaverApiService.BASE_URL)
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get())
            .build()
    }

}