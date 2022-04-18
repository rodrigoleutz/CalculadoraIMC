package br.com.uware.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.navigation.ImcNavigation
import br.com.uware.calculadoraimc.widgets.DrawerLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * MainActivity
 * Jetpack Compose Activity
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load MainViewModel
        val mainViewModel: MainViewModel by viewModels()
        setContent {
            ImcNavigation(mainViewModel = mainViewModel)
        }
    }
}

/**
 * MyApp
 * Base to load screens in app.
 * @param mainViewModel MainViewModel
 * @param drawerIcon { Unit } for drawer icon = Menu.
 * @param content { Unit } for content in screen.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Composable
fun MyApp(
    mainViewModel: MainViewModel,
    drawerIcon: @Composable () -> Unit = {
        IconButton(onClick = {
            mainViewModel.coroutineScope?.launch {
                mainViewModel.drawerState?.open()
            }
        }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.description_menu)
            )
        }
    },
    content: @Composable () -> Unit
) {
    MaterialTheme {
        MainContent(mainViewModel = mainViewModel, drawerIcon = { drawerIcon() }) {
            content()
        }
    }
}

/**
 * MainContent
 * Base of screen content.
 * @param mainViewModel MainViewModel
 * @param drawerIcon { Unit } for drawer icon.
 * @param content { Unit } of content in screen = {}
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Composable
fun MainContent(
    mainViewModel: MainViewModel,
    drawerIcon: @Composable () -> Unit,
    content: @Composable () -> Unit = {}
) {
    DrawerLayout(mainViewModel = mainViewModel) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = colorResource(id = R.color.blue_500),
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    contentColor = Color.White,
                    navigationIcon = {
                        drawerIcon()
                    }
                )
            },
            drawerElevation = 8.dp,
            drawerContentColor = Color.White,
            drawerGesturesEnabled = true,
            backgroundColor = Color.LightGray
        ) {
            content()
        }
    }
}



