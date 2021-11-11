package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


var okHttpClient = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .build()
/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object to convert JSON fetch appropiatly
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .client(okHttpClient)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface AsteroidApiService {
    @GET("neo/rest/v1/feed")
    fun getAllAsteroids(@Query("api_key") apiKey: String = Constants.API_KEY) : Call<String>

}

/**
 * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
 */
object AsteroidApi {
    val asteriodApi : AsteroidApiService by lazy {
    retrofit.create(AsteroidApiService::class.java)}
}

