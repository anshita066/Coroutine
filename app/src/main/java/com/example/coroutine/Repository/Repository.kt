package com.example.coroutine.Repository

import com.example.coroutine.retrofit.RetrofitService

class Repository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllData() = retrofitService.getAllData()

}
