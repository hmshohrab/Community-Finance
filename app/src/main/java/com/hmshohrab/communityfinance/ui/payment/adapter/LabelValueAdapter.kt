package com.hmshohrab.communityfinance.ui.payment.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hmshohrab.communityfinance.core.SimpleCallback
import com.hmshohrab.communityfinance.data.model.LabelValueItem
import com.hmshohrab.communityfinance.databinding.RowLabelValueItemBinding

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 02,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
class LabelValueAdapter : RecyclerView.Adapter<LabelValueAdapter.ItemHolder>() {

    inner class ItemHolder(val binding: RowLabelValueItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<LabelValueItem>() {
        override fun areItemsTheSame(oldItem: LabelValueItem, newItem: LabelValueItem): Boolean {
            return oldItem.label == newItem.label
        }

        override fun areContentsTheSame(oldItem: LabelValueItem, newItem: LabelValueItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        val binding =
            RowLabelValueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                txtLabel.text = it.label
                txtValue.text = it.value
            }
        }
    }
}
