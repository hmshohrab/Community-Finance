package com.hmshohrab.communityfinance.ui.category.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.data.model.Category
import com.hmshohrab.communityfinance.databinding.SingleRowItemBinding
import com.hmshohrab.communityfinance.ui.discount.adapter.DiscountPartnerAdapter

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 01,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class CategoriesAdapter :  RecyclerView.Adapter<CategoriesAdapter.ItemHolder>() {
    var listener: SimpleCallback<Category>? = null
    inner class ItemHolder(val binding: SingleRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val binding =
            SingleRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = differ.currentList.get(position)
//        holder
        holder.binding.apply {
            item?.let {
                txtTitle.text = it.name
                root.setOnClickListener {
                    listener?.callback(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }



}

