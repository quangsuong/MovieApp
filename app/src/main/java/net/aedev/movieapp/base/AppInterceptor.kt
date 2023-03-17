package net.aedev.movieapp.base

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor(private val context: Context) : Interceptor {
    /**
     * This class define header of api, please confirm with server to define header of api
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val initRequest = chain.request()

        val request = initRequest.newBuilder()
            .addHeader("CLIENT_OS", "android")
            //.addHeader("APP_VERSION_NAME", BuildConfig.VERSION_NAME)
            //.addHeader("APP_VERSION_CODE", "${BuildConfig.VERSION_CODE}")
            .build()
        return chain.proceed(request)
    }
}