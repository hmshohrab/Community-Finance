package com.hmshohrab.communityfinance.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.net.Uri
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 * BISMILLAH HIR RAHMAN NIR RAHIM
 * Created by Mohammad Shohrab on 02,March,2023
 * @Company Data Grid Limited
 * @Address House #14/A(new), Dhanmondi R/A, Dhaka 1209.
 * @Email shohrab.datagridltd@gmail.com
 */
object Utils {
    fun getLocalTime(): String {
        val currentDate = Calendar.getInstance()
        //  return currentDate.timeInMillis + TimeZone.getDefault().getOffset(currentDate.timeInMillis)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale.getDefault())
        return sdf.format(currentDate.timeInMillis)
    }
     fun viewToImage(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }
}