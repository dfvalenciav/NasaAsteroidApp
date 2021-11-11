package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    /*property for calling */
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getAllAsteroids()
    }
    /*fun for calling retrofit interface*/
    private fun getAllAsteroids () {
            AsteroidApi.asteriodApi.getAllAsteroids().enqueue(object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()?.let {
                        parseAsteroidsJsonResult(JSONObject(it)).size.toString()
                    }
                    Log.e("probe","${_response.value}")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "failure" + t.message
                    Log.e("probe","failure")
                }

            })

    }


}