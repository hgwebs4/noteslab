package org.hgwebs.noteslab.ui.page.settings

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FormatTextdirectionRToL
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.hgwebs.noteslab.R
import org.hgwebs.noteslab.data.LocalLanguages
import org.hgwebs.noteslab.data.preference.LanguagesPreference
import org.hgwebs.noteslab.ui.component.BackButton
import org.hgwebs.noteslab.ui.component.PreferenceSubtitle
import org.hgwebs.noteslab.ui.component.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguagesPage(
    navigateToTextDirection: () -> Unit,
    onPopBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val fraction =
        CubicBezierEasing(1f, 0f, 0.8f, 0.4f).transform(scrollBehavior.state.overlappedFraction)
    val context = LocalContext.current
    val languages = LocalLanguages.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.languages),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = fraction)
                    )
                },
                navigationIcon = { BackButton(onClick = onPopBack) },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item(key = languages.value + 10000) {
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = stringResource(id = R.string.languages),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            items(LanguagesPreference.values, key = { it.value }) {
                SettingItem(
                    title = it.getDesc(context),
                    onClick = { it.put(context = context, scope = scope) }
                ) {
                    RadioButton(selected = it == languages, onClick = {
                        it.put(context = context, scope = scope)
                    })
                }
            }
            item {
                PreferenceSubtitle(text = stringResource(id = R.string.other))
            }
            item {
                SettingItem(
                    title = stringResource(id = R.string.content_text_direction),
                    description = stringResource(id = R.string.content_text_direction_desc),
                    icon = Icons.Outlined.FormatTextdirectionRToL,
                    onClick = navigateToTextDirection,
                )
            }
        }
    }
}