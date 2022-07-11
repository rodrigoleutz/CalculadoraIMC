package br.com.uware.calculadoraimc.navigation

import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.MyApp
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.screens.detail.DetailScreen
import br.com.uware.calculadoraimc.screens.home.HomeScreen
import br.com.uware.calculadoraimc.screens.imc.ImcScreen
import br.com.uware.calculadoraimc.screens.saved.SavedScreen
import br.com.uware.calculadoraimc.screens.splash.SplashScreen
import kotlinx.coroutines.launch
import java.util.*


/**
 * ImcNavigation
 * Navigation in app.
 * @param mainViewModel ViewModel da aplicação.
 * @author Rodrigo Leutz
 */
@Composable
fun ImcNavigation(mainViewModel: MainViewModel) {
    // Set variables of mainViewModel
    mainViewModel.navHostController = rememberNavController()
    mainViewModel.drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    mainViewModel.coroutineScope = rememberCoroutineScope()

    // Routes
    NavHost(
        navController = mainViewModel.navHostController!!,
        startDestination = ImcScreens.SplashScreen.name
    ) {
        composable(ImcScreens.SplashScreen.name) {
            SplashScreen(mainViewModel = mainViewModel)
        }
        composable(ImcScreens.HomeScreen.name) {
            MyApp(mainViewModel = mainViewModel) {
                HomeScreen(mainViewModel = mainViewModel)
            }
        }
        composable(ImcScreens.ImcScreen.name) {
            MyApp(mainViewModel = mainViewModel) {
                ImcScreen(mainViewModel = mainViewModel)
            }
        }
        composable(ImcScreens.SavedScreen.name) {
            MyApp(mainViewModel = mainViewModel) {
                SavedScreen(mainViewModel = mainViewModel)
            }
        }
        composable(ImcScreens.DetailScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) {
            MyApp(mainViewModel = mainViewModel, drawerIcon = {
                IconButton(onClick = {
                    mainViewModel.coroutineScope?.launch {
                        mainViewModel.navHostController?.popBackStack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.description_menu)
                    )
                }
            }) {
                DetailScreen(mainViewModel, UUID.fromString(it.arguments?.getString("id")))
            }
        }
    }
}