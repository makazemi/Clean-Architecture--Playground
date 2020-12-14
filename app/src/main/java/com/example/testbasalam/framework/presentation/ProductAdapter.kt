package com.example.testbasalam.framework.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.testbasalam.R
import com.example.testbasalam.business.domain.model.Product
import com.example.testbasalam.business.domain.model.setPostfixPrice
import com.example.testbasalam.business.domain.model.setPostfixWeight
import com.example.testbasalam.business.domain.model.setRate
import com.example.testbasalam.databinding.ProductItemRcyBinding


class ProductAdapter(private val context:Context,private val requestManager: RequestManager) : ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallback()) {

    private lateinit var clickListener: (item: Product) -> Unit

    fun setClickListener(clickListener: (item: Product) -> Unit) {
        this.clickListener = clickListener
    }


    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding=ProductItemRcyBinding.bind(itemView)
        fun bind(item: Product) {
            binding.txtName.text=item.name
            binding.txtVendor.text=item.vendor
            binding.txtWeight.text=item.setPostfixPrice(context.getString(R.string.gram))
            binding.txtPrice.text=item.setPostfixWeight(context.getString(R.string.toman))
            binding.txtRating.text=item.setRate()

            requestManager.load(item.photo)
                .error(R.drawable.img_placeholder)
                .placeholder(R.drawable.img_placeholder)
                .thumbnail(0.1f)
                .into(binding.imgProduct)

        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.product_item_rcy, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}