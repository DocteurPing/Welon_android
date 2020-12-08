package com.welon.android.utils

import android.annotation.SuppressLint
import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleyRequest(
    val url: String,
    val result: (String) -> Unit,
    val error: (String) -> Unit
) {

    fun makeRequestToJSON(method: Int, header: HashMap<String, String>, params: HashMap<String, String>, body: String) {
        val req = object : StringRequest(method, url, { res ->
            result(res.toString().trim())
        }, { volleyError ->
            error(volleyError.message.toString())
        }) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }

            override fun getBody(): ByteArray {
                return body.toByteArray(Charsets.UTF_8)
            }

            override fun getHeaders(): MutableMap<String, String> {
                return header
            }
        }
        volley.add(req)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        val volley: RequestQueue by lazy {
            Volley.newRequestQueue(
                context
                    ?: throw NullPointerException(" Initialize WolfRequest in application class")
            )
        }

        fun init(context: Context) {
            Companion.context = context
        }
    }
}