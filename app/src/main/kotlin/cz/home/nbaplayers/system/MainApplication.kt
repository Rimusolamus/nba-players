package cz.home.nbaplayers.system

import android.app.Application
import cz.home.nbaplayers.feature.allplayers.di.allPlayersModule
import cz.home.nbaplayers.feature.playerdetails.di.playerDetailsModule
import cz.home.nbaplayers.feature.teamdetails.di.teamDetailModule
import cz.home.nbaplayers.library.networking.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

internal class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            // modules to load
            modules(
                // feature modules
                allPlayersModule,
                playerDetailsModule,
                teamDetailModule,
                // library modules
                networkingModule
            )
        }
    }
}