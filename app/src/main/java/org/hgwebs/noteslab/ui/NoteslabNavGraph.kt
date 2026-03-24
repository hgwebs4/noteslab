package org.hgwebs.noteslab.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.FOLDER_ID_ARG
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.IS_PREVIEW_ARG
import org.hgwebs.noteslab.ui.NoteslabDestinationsArgs.NOTE_ID_ARG
import org.hgwebs.noteslab.ui.ext.animatedComposable
import org.hgwebs.noteslab.ui.page.login.LoginPage
import org.hgwebs.noteslab.ui.page.note_detail.NoteDetailPage
import org.hgwebs.noteslab.ui.page.notes.NotesPage
import org.hgwebs.noteslab.ui.page.search.SearchPage
import org.hgwebs.noteslab.ui.page.settings.*
import org.hgwebs.noteslab.ui.page.settings.accounts.AccountDetailPage
import org.hgwebs.noteslab.ui.page.settings.tools.ToolsPage
import org.hgwebs.noteslab.ui.page.settings.tools.database.DatabaseStatusPage
import org.hgwebs.noteslab.ui.page.settings.tools.log.LogPage

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteslabNavGraph(
    navController: NavHostController = rememberNavController(),
    navigationActions: NoteslabNavigationActions = remember(navController) {
        NoteslabNavigationActions(navController)
    },
    startDestination: String = NoteslabDestinations.NOTES_ROUTE
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ) {
        animatedComposable(NoteslabDestinations.NOTES_ROUTE) {
            NotesPage(
                navigateToNote = { noteId ->
                    navigationActions.navigateToNote(noteId = noteId)
                },
                navigateToNewNote = { folderId ->
                    navigationActions.navigateToNewNote(folderId = folderId)
                },
                navigateToLogin = {
                    navigationActions.navigateToLogin()
                },
                navigateToSettings = {
                    navigationActions.navigateToSettings()
                },
                navigateToSearch = {
                    navigationActions.navigateToSearch()
                }
            )
        }
        animatedComposable(
            NoteslabDestinations.NOTE_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.StringType; nullable = true },
                navArgument(FOLDER_ID_ARG) { type = NavType.StringType; nullable = true },
                navArgument(IS_PREVIEW_ARG) { type = NavType.BoolType; defaultValue = false },
            )
        ) {
            NoteDetailPage(
                navigateToNote = {
                    navigationActions.navigateToNote(it)
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
        animatedComposable(NoteslabDestinations.LOGIN_ROUTE) {
            LoginPage(
                onLoginSuccess = {
                    navController.popBackStack()
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
        animatedComposable(NoteslabDestinations.SETTINGS_ROUTE) {
            SettingsPage(
                navigateToAccountDetail = {
                    navigationActions.navigateToAccountDetail()
                },
                navigateToTools = {
                    navigationActions.navigateToTools()
                },
                navigateToAbout = {
                    navigationActions.navigateToAbout()
                },
                navigateToLanguages = {
                    navigationActions.navigateToLanguages()
                },
                navigateToAppearance = {
                    navigationActions.navigateToAppearance()
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
        animatedComposable(NoteslabDestinations.ACCOUNT_DETAIL_ROUTE) {
            AccountDetailPage(
                navigateToLogin = {
                    navigationActions.navigateToLogin()
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
        animatedComposable(NoteslabDestinations.TOOLS_ROUTE) {
            ToolsPage(
                navigateToLogDetail = { navigationActions.navigateToLog() },
                navigateToDatabaseStatus = { navigationActions.navigateToDatabaseStatus() }
            ) {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.LOG_ROUTE) {
            LogPage {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.DATABASE_STATUS_ROUTE) {
            DatabaseStatusPage {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.SEARCH_ROUTE) {
            SearchPage(navigateToNote = { noteId ->
                navigationActions.navigateToNote(noteId)
            }) {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.ABOUT_ROUTE) {
            AboutPage(navigateToCredits = {
                navigationActions.navigateToCredits()
            }) {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.CREDITS_ROUTE) {
            CreditsPage {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.LANGUAGES_ROUTE) {
            LanguagesPage(navigateToTextDirection = {
                navigationActions.navigateToTextDirection()
            }) {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.APPEARANCE_ROUTE) {
            AppearancePage(navigateToDarkTheme = {
                navigationActions.navigateToDarkTheme()
            }) {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.DARK_THEME_ROUTE) {
            DarkThemePage {
                navController.popBackStack()
            }
        }
        animatedComposable(NoteslabDestinations.TEXT_DIRECTION_ROUTE) {
            TextDirectionPage {
                navController.popBackStack()
            }
        }
    }
}
