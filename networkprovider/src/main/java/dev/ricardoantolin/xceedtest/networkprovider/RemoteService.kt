package dev.ricardoantolin.xceedtest.networkprovider

import com.google.gson.GsonBuilder
import dev.ricardoantolin.xceedtest.networkprovider.executors.JobExecutor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.sZ"

data class RemoteServiceConfig(
    val debug: Boolean,
    val baseUrl: String
)

abstract class RemoteService<T>
constructor(c: Class<T>, private val config: RemoteServiceConfig) {
    protected var service: T

    init {
        service = initApiService().create(c)
    }

    private fun initApiService(): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())

        val gson = GsonBuilder().setDateFormat(API_DATE_FORMAT).create()

        return Retrofit.Builder().baseUrl(config.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callbackExecutor(JobExecutor())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(builder.build()).build()
    }

    private fun getLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()
        .apply {
            level = if (config.debug)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
}