package com.morbis.spe.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Base64

class SessionPref {
    private var sharedPreferences: SharedPreferences? = null
    private var spEditor: SharedPreferences.Editor? = null
    private val NAME_SESSION = "BroTenton"
    val KOSONG = "tenton"


    companion object {
        @SuppressLint("CommitPrefEdits")
        fun instance(context: Context): SessionPref {
            val session = SessionPref()
            session.sharedPreferences =
                context.getSharedPreferences(session.NAME_SESSION, Context.MODE_PRIVATE)
            session.spEditor = session.sharedPreferences!!.edit()
            return session
        }
    }

}