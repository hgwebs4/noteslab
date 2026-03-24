package org.hgwebs.noteslab.ui

import android.net.Uri
import androidx.navigation.NavHostController
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.FOLDER_ID_ARG
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.IS_PREVIEW_ARG
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.NOTE_ID_ARG
import org.hgwebs.noteslab.ui.NoteslabPages.ABOUT_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.ACCOUNT_DETAIL_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.APPEARANCE_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.CREDITS_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.DARK_THEME_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.DATABASE_STATUS_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.LANGUAGES_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.LOGIN_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.LOG_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.NOTES_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.NOTE_DETAIL_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.SEARCH_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.SETTINGS_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.TEXT_DIRECTION_PAGE
import org.hgwebs.noteslab.ui.NoteslabPages.TOOLS_PAGE

object NoteslabPages {
    const val NOTES_PAGE = "notes"
    const val NOTE_DETAIL_PAGE = "note_detail"
    const val LOGIN_PAGE = "login"
    const val SETTINGS_PAGE = "settings"
    const val ACCOUNT_DETAIL_PAGE = "account_detail"
    const val TOOLS_PAGE = "tools"
    const val LOG_PAGE = "log"
    const val DATABASE_STATUS_PAGE = "database_status"
    const val SEARCH_PAGE = "search"
    const val ABOUT_PAGE = "about"
    const val CREDITS_PAGE = "credits"
    const val LANGUAGES_PAGE = "languages"
    const val APPEARANCE_PAGE = "appearance"
    const val DARK_THEME_PAGE = "dark_theme"
    const val TEXT_DIRECTION_PAGE = "text_direction"
}

object NoteslabDestinationsArgs {
    const val NOTE_ID_ARG = "noteId"
    const val FOLDER_ID_ARG = "folderId"
    const val IS_PREVIEW_ARG = "isPreview"
}

object NoteslabDestinations {
    const val NOTES_ROUTE = NOTES_PAGE
    const val NOTE_DETAIL_ROUTE =
        "$NOTE_DETAIL_PAGE?$NOTE_ID_ARG={$NOTE_ID_ARG}&$FOLDER_ID_ARG={$FOLDER_ID_ARG}&$IS_PREVIEW_ARG={$IS_PREVIEW_ARG}"
    const val LOGIN_ROUTE = LOGIN_PAGE
    const val SETTINGS_ROUTE = SETTINGS_PAGE
    const val ACCOUNT_DETAIL_ROUTE = ACCOUNT_DETAIL_PAGE
    const val TOOLS_ROUTE = TOOLS_PAGE
    const val LOG_ROUTE = LOG_PAGE
    const val DATABASE_STATUS_ROUTE = DATABASE_STATUS_PAGE
    const val SEARCH_ROUTE = SEARCH_PAGE
    const val ABOUT_ROUTE = ABOUT_PAGE
    const val CREDITS_ROUTE = CREDITS_PAGE
    const val LANGUAGES_ROUTE = LANGUAGES_PAGE
    const val APPEARANCE_ROUTE = APPEARANCE_PAGE
    const val DARK_THEME_ROUTE = DARK_THEME_PAGE
    const val TEXT_DIRECTION_ROUTE = TEXT_DIRECTION_PAGE
}

class NoteslabNavigationActions(private val navController: NavHostController) {
    fun navigateToNote(noteId: String, isPreview: Boolean = true) {
        val builder = Uri.Builder()
        builder.path(NOTE_DETAIL_PAGE)
        builder.appendQueryParameter(NOTE_ID_ARG, noteId)
        builder.appendQueryParameter(IS_PREVIEW_ARG, isPreview.toString())
        val url = builder.build().toString()
        navController.navigate(url)
    }

    fun navigateToNewNote(folderId: String?) {
        val builder = Uri.Builder()
        builder.path(NOTE_DETAIL_PAGE)
        if (folderId != null) {
            builder.appendQueryParameter(FOLDER_ID_ARG, folderId)
        }
        val url = builder.build().toString()
        navController.navigate(url) {
            launchSingleTop = true
        }
    }

    fun navigateToLogin() {
        navController.navigate(NoteslabDestinations.LOGIN_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToSettings() {
        navController.navigate(NoteslabDestinations.SETTINGS_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToAccountDetail() {
        navController.navigate(NoteslabDestinations.ACCOUNT_DETAIL_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToTools() {
        navController.navigate(NoteslabDestinations.TOOLS_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToLog() {
        navController.navigate(NoteslabDestinations.LOG_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToDatabaseStatus() {
        navController.navigate(NoteslabDestinations.DATABASE_STATUS_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToSearch() {
        navController.navigate(NoteslabDestinations.SEARCH_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToAbout() {
        navController.navigate(NoteslabDestinations.ABOUT_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToCredits() {
        navController.navigate(NoteslabDestinations.CREDITS_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToLanguages() {
        navController.navigate(NoteslabDestinations.LANGUAGES_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToAppearance() {
        navController.navigate(NoteslabDestinations.APPEARANCE_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToDarkTheme() {
        navController.navigate(NoteslabDestinations.DARK_THEME_ROUTE) {
            launchSingleTop = true
        }
    }

    fun navigateToTextDirection() {
        navController.navigate(NoteslabDestinations.TEXT_DIRECTION_ROUTE) {
            launchSingleTop = true
        }
    }
}
