package com.nhdtech.apps.data.network.interceptor

import com.nhdtech.apps.core.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }
}