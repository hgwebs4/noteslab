package org.hgwebs.noteslab

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import org.hgwebs.noteslab.data.NotesRepository
import org.hgwebs.noteslab.di.ApplicationScope
import javax.inject.Inject

@HiltAndroidApp
class NoteslabApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var notesRepository: NotesRepository

    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()

    override fun onCreate() {
        super.onCreate()
        notesRepository.doSync(isOnStart = true, fromScratch = false)
    }
}
