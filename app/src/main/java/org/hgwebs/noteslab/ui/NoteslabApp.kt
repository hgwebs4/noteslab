package org.hgwebs.noteslab.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.hgwebs.noteslab.ui.theme.NoteslabTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NoteslabApp() {
    NoteslabTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NoteslabNavigationActions(navController)
        }
        NoteslabNavGraph(
            navController = navController,
            navigationActions = navigationActions
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteslabTheme {
        Greeting("Android")
    }
}
