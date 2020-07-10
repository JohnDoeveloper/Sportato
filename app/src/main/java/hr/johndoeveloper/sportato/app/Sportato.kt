package hr.johndoeveloper.sportato.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Sportato: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        private lateinit var instance: Sportato
        fun getAppContext() : Context = instance.applicationContext
    }

}