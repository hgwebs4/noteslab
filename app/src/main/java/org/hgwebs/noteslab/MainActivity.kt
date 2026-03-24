package org.hgwebs.noteslab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import org.hgwebs.noteslab.data.DataStoreKeys
import org.hgwebs.noteslab.data.SettingsProvider
import org.hgwebs.noteslab.data.dataStore
import org.hgwebs.noteslab.data.get
import org.hgwebs.noteslab.data.languages
import org.hgwebs.noteslab.data.preference.DarkThemePreference
import org.hgwebs.noteslab.data.preference.LanguagesPreference
import org.hgwebs.noteslab.ui.NoteslabApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Manually set the postSplashScreenTheme
        setTheme(
            when (DarkThemePreference.fromInt(dataStore.get(DataStoreKeys.DarkTheme))) {
                DarkThemePreference.UseDeviceTheme -> R.style.Theme_Noteslab
                DarkThemePreference.ON -> R.style.Theme_Noteslab_Dark
                DarkThemePreference.OFF -> R.style.Theme_Noteslab_Light
            }
        )
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        LanguagesPreference.fromValue(languages).let {
            if (it == LanguagesPreference.UseDeviceLanguages) return@let
            it.setLocale(this)
        }

        setContent {
            SettingsProvider {
                NoteslabApp()
            }
        }
    }
}
