package com.hmshohrab.communityfinance.manager

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmshohrab.communityfinance.R
import com.hmshohrab.communityfinance.data.model.LabelValueItem
import com.hmshohrab.communityfinance.databinding.ConfirmDialogBinding
import com.hmshohrab.communityfinance.databinding.LayoutReceiptBinding
import com.hmshohrab.communityfinance.ui.payment.adapter.LabelValueAdapter
import com.hmshohrab.communityfinance.utils.hide
import com.hmshohrab.communityfinance.utils.invisible

/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 02,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
object DialogManager {
    fun openConfirmDialog(
        context: Context,
        list: MutableList<LabelValueItem>,
        confirm: () -> Unit
    ) {
        val dialog = Dialog(context)
        val dialogBinding = ConfirmDialogBinding.inflate(LayoutInflater.from(context))
        dialog.window?.setLayout(
            (context.resources.displayMetrics.widthPixels.toDouble() * 0.98).toInt(),
            (context.resources.displayMetrics.heightPixels.toDouble() * 0.98).toInt()
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        dialog.apply {
            setContentView(dialogBinding.root)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(true)
            window?.attributes?.windowAnimations = R.style.animation

            dialogBinding.apply {
                rvLevel.apply {
                    val labelValueAdapter = LabelValueAdapter()
                    layoutManager = LinearLayoutManager(context)
                    adapter = labelValueAdapter
                    labelValueAdapter.differ.submitList(list)
                }
                btnConfirm.setOnClickListener {
                    confirm()
                    dismiss()
                }
            }
            show()
        }
    }

    fun openReceiptDialog(
        context: Context,
        hashMap: HashMap<String, String>,
        callback: (View) -> Unit
    ) {
        val dialog = Dialog(context)
        val dialogBinding = LayoutReceiptBinding.inflate(LayoutInflater.from(context))
        dialog.window?.setLayout(
            (context.resources.displayMetrics.widthPixels.toDouble() * 0.98).toInt(),
            (context.resources.displayMetrics.heightPixels.toDouble() * 0.98).toInt()
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        dialog.apply {
            setContentView(dialogBinding.root)
            window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setCancelable(true)
            window?.attributes?.windowAnimations = R.style.animation
            dialogBinding.apply {
                txtDestAccountNo.text = hashMap["number"]
                txtAmount.text = hashMap["amount"] + ".00"
                txtNarration.text = hashMap["narration"]
                imgIcon.setImageResource(hashMap["payIcon"].toString().toInt())
                txtTransactionDateTime.text = hashMap["time"]
                txtTotalAmount.text = "BDT ${hashMap["amount"]}.00"

                btnDownloadReceipt.setOnClickListener {
                    dialogBinding.btnDownloadReceipt.hide()
                    dialogBinding.imgClose.invisible()
                    callback(dialogBinding.root)
                    dismiss()
                }
                imgClose.setOnClickListener { dismiss() }
            }
            show()
        }
    }

}