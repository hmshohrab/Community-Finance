package com.hmshohrab.communityfinance.ui.discount.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.data.model.DiscountPartner
import com.hmshohrab.communityfinance.databinding.SingleRowItemBinding

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 01,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class DiscountPartnerAdapter :  RecyclerView.Adapter<DiscountPartnerAdapter.ItemHolder>() {
    var listener: SimpleCallback<DiscountPartner>? = null
    inner class ItemHolder(val binding: SingleRowItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<DiscountPartner>() {
        override fun areItemsTheSame(oldItem: DiscountPartner, newItem: DiscountPartner): Boolean {
            return oldItem.discount_id == newItem.discount_id
        }

        override fun areContentsTheSame(oldItem: DiscountPartner, newItem: DiscountPartner): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val binding =
            SingleRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                txtTitle.text = it.partner_name
                root.setOnClickListener {
                    listener?.callback(item)
                }
            }
        }
    }
}

