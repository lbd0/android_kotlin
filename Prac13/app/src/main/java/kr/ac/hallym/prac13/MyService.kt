package kr.ac.hallym.prac13

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    class MyBinder: Binder(){
        fun funA(arg: Int) {
            Log.d("week15", "funA....$arg")
        }
        fun funB(arg:Int):Int{
            Log.d("week15","funB....$arg")
            return arg * arg
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("week15", "service onBind...")
        return MyBinder()
    }
}