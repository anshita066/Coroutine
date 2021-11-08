package com.example.coroutine.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutine.Model.Model
import com.example.coroutine.Repository.Repository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: Repository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val dataList = MutableLiveData<List<Model>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    //Getting data from API
    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    dataList.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
