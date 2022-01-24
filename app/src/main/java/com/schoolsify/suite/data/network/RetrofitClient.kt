package com.fbn.fbnquest.data.network

import android.content.Context
import com.schoolsify.suite.Constant
import okhttp3.*
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitClient {
    var mRetrofit: Retrofit? = null

    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        val mBuilder :Retrofit.Builder =  Retrofit.Builder()
        return mBuilder
                .baseUrl(Constant.BASE_URL)
                .client(getRetrofitClient(context = context))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)

    }


 open fun provideCacheInterceptor(): Interceptor {
     return Interceptor { chain: Interceptor.Chain ->
         val response = chain.proceed(chain.request())

         // re-write response header to force use of cache
         val cacheControl: CacheControl = CacheControl.Builder()
                 .maxAge(2, TimeUnit.MINUTES)
                 .build()
         response.newBuilder()
                 .header("Cache-Control", cacheControl.toString())
                 .removeHeader("Pragma")
                 .build()
     }
 }
 private fun provideOfflineCacheInterceptor(): Interceptor {

        return Interceptor label@{ chain: Interceptor.Chain ->
            try {
                return@label chain.proceed(chain.request())
            } catch (e: Exception) {

                //Log.i("mCacheMaxStale", String.valueOf(userPreferences.getCacheControlMaxStale()));
                val cacheControl: CacheControl = CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                val offlineRequest = chain.request().newBuilder()
                        .cacheControl(cacheControl)
                        .removeHeader("Pragma")
                        .build()
                return@label chain.proceed(offlineRequest)
            }
        }
    }

    private fun getRetrofitClient(authenticator: Authenticator? = null, context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().also {
                            it.addHeader("accept", "*/*")
                           // it.addHeader("Content-Type", "application/json")
                        }.build()
                    )
                }.also { client ->
                    authenticator?.let { client.authenticator(it) }
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)


                    client.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                            .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                            .readTimeout(5, TimeUnit.MINUTES)

                }.build()

    }
}