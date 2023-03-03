package com.hmshohrab.communityfinance.ui.dashboard.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.data.model.Item
import com.hmshohrab.communityfinance.databinding.RowItemBinding

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 01,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class PaymentListAdapter :  RecyclerView.Adapter<PaymentListAdapter.ItemHolder>() {
    var listener: SimpleCallback<Item>? = null
    inner class ItemHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val binding =
            RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = differ.currentList.get(position)
//        holder
        holder.binding.apply {
            item?.let {
                txtTitle.text = it.title
                imgIcon.setImageResource(it.icon)
                
                root.setOnClickListener {
                    listener?.callback(item)
                }
            }
        }
    }
}

