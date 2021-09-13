package com.hth96.mvvmjetpack.data

import androidx.lifecycle.MutableLiveData
import com.hth96.mvvmjetpack.api.RubbishService
import com.hth96.mvvmjetpack.resource.Resource
import com.hth96.mvvmjetpack.util.handleError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RubbishRepository @Inject constructor(
    private val rubbishService: RubbishService
) {

    // Lay danh sach town city
    val getTownCitiesResult = MutableLiveData<Resource<List<String?>?>>()

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)

    // Lay danh sach town city
    fun getTownCities() {
        getTownCitiesResult.postValue(Resource.Loading("Getting town city list", getTownCitiesResult.value?.data))
        ioScope.launch {
            try {
                val result = rubbishService.getTownCities()
                if (result.isSuccessful && !result.body().isNullOrEmpty()) {
                    // Luu lai danh sach town city
                    getTownCitiesResult.postValue(Resource.Success(result.body()?.filter { s -> !s.isNullOrEmpty() && !s.contentEquals("town_city") }))
                } else {
                    handleError(getTownCitiesResult, "No town city founds!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                handleError(getTownCitiesResult, "Getting town cities error: ${e.message}")
            }
        }
    }
}