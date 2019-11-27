package com.example.pure_dagger_movie_example.data.di.app

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.pure_dagger_movie_example.utils.Constants
import com.example.pure_dagger_movie_example.R
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkHttpModule {

    @Provides
    @Singleton
    fun providesInterceptionToRetrofit(): Interceptor {
        return Interceptor { chain ->
            val interceptedUrl = chain
                .request()
                .url()
                .newBuilder()
                .addQueryParameter(
                    Constants.KEY,
                    Constants.API_KEY
                ).build()

            val request = chain
                .request()
                .newBuilder()
                .url(interceptedUrl)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesHttpClientToRetrofit(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.color.colorPrimaryDark)
            .error(R.color.colorAccent)
    }

    @Singleton
    @Provides
    fun providesGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }


}