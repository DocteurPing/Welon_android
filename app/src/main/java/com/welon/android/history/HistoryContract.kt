package com.welon.android.history

import android.content.Context
import com.welon.android.utils.Command

class HistoryContract {
    interface View {
        fun getAppContext(): Context
        fun getToken(): String?
        fun navigateToCommand(commandId: String)
        fun updateCommand(listCommand: ArrayList<Command>)
    }

    interface Presenter {
        fun getListHistory()
    }
}