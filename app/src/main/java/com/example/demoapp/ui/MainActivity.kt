package com.example.demoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.R
import com.example.demoapp.other.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = EmployeeAdapter()
        rvEmployees.layoutManager = LinearLayoutManager(this)
        rvEmployees.adapter = adapter

        mainViewModel.res.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progress.visibility = View.GONE
                    rvEmployees.visibility = View.VISIBLE
                    it.data.let {res->
                        if (res?.status == "success"){
                            res.data?.let { it1 -> adapter.submitList(it1) }
                        }else{
                            Snackbar.make(rootView, "Status = false",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                Status.LOADING -> {
                    progress.visibility = View.VISIBLE
                    rvEmployees.visibility = View.GONE
                }
                Status.ERROR -> {
                    progress.visibility = View.GONE
                    rvEmployees.visibility = View.VISIBLE
                    Snackbar.make(rootView, "Something went wrong",Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}