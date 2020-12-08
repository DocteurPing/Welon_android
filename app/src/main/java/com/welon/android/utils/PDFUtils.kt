package com.welon.android.utils

import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

fun createMyPDF(text: String, path: String) {
    val myPdfDocument = PdfDocument()
    val myPageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
    val myPage = myPdfDocument.startPage(myPageInfo)
    val myPaint = Paint()
    val x = 10
    var y = 25
    myPage.canvas.drawText("WELON", 140f, y.toFloat(), myPaint)
    y += (myPaint.descent().toInt() - myPaint.ascent().toInt()) * 2
    for (line in text.split("\n".toRegex()).toTypedArray()) {
        myPage.canvas.drawText(line, x.toFloat(), y.toFloat(), myPaint)
        y += myPaint.descent().toInt() - myPaint.ascent().toInt()
    }
    myPdfDocument.finishPage(myPage)
    val myFilePath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/" + path
    val myFile = File(myFilePath)
    try {
        myPdfDocument.writeTo(FileOutputStream(myFile))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    myPdfDocument.close()
}