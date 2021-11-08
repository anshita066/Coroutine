package com.example.coroutine

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutine.Repository.Repository
import com.example.coroutine.ViewModel.MainViewModel
import com.example.coroutine.ViewModel.MyViewModelFactory
import com.example.coroutine.adapter.CustomAdapter
import com.example.coroutine.retrofit.RetrofitService

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val adapter = CustomAdapter()
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.post_details_RV)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = Repository(retrofitService)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


        //Observing livedata
        viewModel.dataList.observe(this, {
            adapter.setData(it)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getAllMovies()



    }
}