package com.example.login_practice

import android.content.Context

class ykShareperfress private constructor(){
    private val file_name = "ykFile"
    private val KEY = "ykKey"

    private val Afile_name = "aYkFile"
    private val Akey = "aykKey"

    companion object{
        private var instant:ykShareperfress? = null
        private var mContext: Context? = null

//        创建单例对象
        fun getInstance(context: Context):ykShareperfress{
            mContext = context
            if (instant == null){
                instant = ykShareperfress()
            }
            return instant!!
        }
    }
    fun savePassward(password: String){
        mContext?.getSharedPreferences(file_name,Context.MODE_PRIVATE).also {
            it?.edit().also {yk ->
                yk?.putString(KEY,password)
                yk?.apply()
            }
        }
    }

    fun saveAccount(account:String){
        mContext?.getSharedPreferences(Afile_name,Context.MODE_PRIVATE).also {
            it?.edit().also { yk ->
                yk?.putString(Akey,account)
                yk?.apply()
            }
        }
    }

    fun getPassword():String?{
        mContext?.getSharedPreferences(file_name, Context.MODE_PRIVATE).also {
            return it?.getString(KEY,null)
        }
    }

    fun getAccount():String?{
        mContext?.getSharedPreferences(Afile_name,Context.MODE_PRIVATE).also {
            return it?.getString(Akey,null)
        }
    }
}