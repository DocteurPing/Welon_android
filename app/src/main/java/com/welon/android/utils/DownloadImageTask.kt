package com.welon.android.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.io.InputStream
import java.net.URL


class DownloadImageTask(private var bmImage: ImageView) :
    AsyncTask<String?, Void?, Bitmap?>() {

    override fun onPostExecute(result: Bitmap?) {
        if (result != null) bmImage.setImageBitmap(result)
    }

    override fun doInBackground(vararg p0: String?): Bitmap? {
        val urldisplay = p0[0]
        var mIcon11: Bitmap? = null
        try {
            val input: InputStream = URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            Log.e("Error", e.message!!)
            e.printStackTrace()
        }
        return mIcon11
    }
}