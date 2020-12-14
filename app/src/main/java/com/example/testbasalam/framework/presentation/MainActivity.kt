package com.example.testbasalam.framework.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.testbasalam.base.BaseActivity
import com.example.testbasalam.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel:ProductViewModel by viewModels()

    private lateinit var productAdapter: ProductAdapter

    @Inject
    lateinit var requestManager: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init(){
        initRcy()
        subscribeObserverProducts()
    }

    private fun initRcy(){
        productAdapter= ProductAdapter(this,requestManager)
        binding.rcyItem.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=productAdapter
        }
        productAdapter.setClickListener {

        }

    }

    private fun subscribeObserverProducts(){
        viewModel.products.observe(this){
            it?.data?.peekContent()?.let {
                productAdapter.submitList(it)
            }
            onDataStateChange(it.loading,it.error,isDialog = false)
        }
    }

    override fun displayProgressBar(inProgress: Boolean) {
        binding.progressbar.visibility= if(inProgress) View.VISIBLE else View.GONE
    }
}