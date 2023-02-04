package com.geektech.homework53

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.geektech.homework53.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ImageAdapter
    var page = 1
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
    }

    private fun initClicker() {
        with(binding) {
            fetchBtn.setOnClickListener {
                doRequest()
            }
            nestedScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                val nv = v as NestedScrollView
                if (scrollY == nv.getChildAt(0).measuredHeight - v.measuredHeight){
                    page++
                    progressBar.isVisible = true
                    doRequest()
                }
            }

        }
    }

    private fun ActivityMainBinding.doRequest() {
        RetrofitService().api.getImage(keyWord = wordEd.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>
                ) {
                    response.body()?.hits?.let {
                        adapter.addList(it)
                        binding.progressBar.isVisible = false
                    }
                        Log.e("ololo", "onResponse: ${response.body()?.hits?.get(0)?.largeImageURL} ")
                }
                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }

            })
        recyclerView.adapter = adapter
    }
}