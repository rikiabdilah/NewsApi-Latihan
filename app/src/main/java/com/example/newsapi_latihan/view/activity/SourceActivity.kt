package com.example.newsapi_latihan.view.activity

import SourceAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi_latihan.R
import com.example.newsapi_latihan.databinding.ActivitySourceBinding
import com.example.newsapi_latihan.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {
    lateinit var binding : ActivitySourceBinding
    lateinit var sourceAdapter : SourceAdapter
    lateinit var sourceVm : SourceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sourceVm = ViewModelProvider(this).get(SourceViewModel::class.java)
        sourceAdapter = SourceAdapter(ArrayList())
        sourceVm.getDataSource().observe(this, Observer {
            sourceAdapter = SourceAdapter(it!!)
            val layoutMan = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvSource.layoutManager = layoutMan
            binding.rvSource.adapter = sourceAdapter
        })

        val datacat = intent.extras!!.getString("name")
        sourceVm.callApiSource(datacat.toString())
    }

}