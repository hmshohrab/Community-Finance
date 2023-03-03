package com.hmshohrab.communityfinance.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class PDFConverter {

    private fun createBitmapFromView(
        context: Context,
        view: View,
    ): Bitmap {
        return createBitmap(context, view)
    }

    private fun createBitmap(
        context: Context,
        view: View
    ): Bitmap {
        /*view.measure(
            // Measure to A4 width
            View.MeasureSpec.makeMeasureSpec(
                595, View.MeasureSpec.EXACTLY
            ),
            // Measure to A4 height
            View.MeasureSpec.makeMeasureSpec(
                842, View.MeasureSpec.EXACTLY
            )
        )
        // Layout the view out to the measure dimensions
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
*/
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display?.getRealMetrics(displayMetrics)
            displayMetrics.densityDpi
        } else {
            (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        val pageHeight = displayMetrics.heightPixels.toFloat().toInt()
        val pageWidth = displayMetrics.widthPixels.toFloat().toInt()
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
            )
        )
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight, Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return Bitmap.createScaledBitmap(bitmap, pageWidth, pageHeight, true)
    }

    private fun convertBitmapToPdf(bitmap: Bitmap, context: Context, fileName: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
        pdfDocument.finishPage(page)
        val simpleDateFormat = SimpleDateFormat("yyyy_MM_dd-HH_MM_a", Locale.getDefault())
        val filePath = File(
            context.getExternalFilesDir(null),
            "$fileName-${simpleDateFormat.format(simpleDateFormat.calendar.time)}.pdf"
        )
        pdfDocument.writeTo(FileOutputStream(filePath))
        pdfDocument.close()
        renderPdf(context, filePath)
    }

    fun createPdf(
        context: Context,
        view: View, fileName: String
    ) {
        val bitmap = createBitmapFromView(context, view)
        convertBitmapToPdf(bitmap, context, fileName)
    }


    private fun renderPdf(context: Context, filePath: File) {
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            filePath
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri, "application/pdf")

        try {
            context.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "No Application available to view pdf", Toast.LENGTH_LONG).show()
        }
    }
}