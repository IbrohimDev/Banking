package uz.gita.banking.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber
import uz.gita.banking.BuildConfig

@HiltAndroidApp
class App:Application() {
    companion object{
        lateinit var instance:App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

    }
}