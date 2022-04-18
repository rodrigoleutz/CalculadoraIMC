package br.com.uware.calculadoraimc.widgets


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.navigation.ImcScreens
import kotlinx.coroutines.*


/**
 * DrawerMenu
 * Navigation Drawer Menu
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Composable
fun DrawerMenu(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorResource(id = R.color.blue_700)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "IMC Icon",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    tint = Color.White
                )
                Text(
                    text = stringResource(id = R.string.imc_calculator),
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
        DrawerMenuButton(
            mainViewModel = mainViewModel,
            text = R.string.home,
            icon = Icons.Rounded.Home,
            navigate = ImcScreens.HomeScreen.name
        )
        DrawerMenuButton(
            mainViewModel = mainViewModel,
            text = R.string.imc_calculator,
            icon = Icons.Rounded.Calculate,
            navigate = ImcScreens.ImcScreen.name
        )
        DrawerMenuButton(
            mainViewModel = mainViewModel,
            text = R.string.saved_screen,
            icon = Icons.Rounded.Save,
            navigate = ImcScreens.SavedScreen.name
        )
    }
}

/**
 * DrawerMenuButton
 * Button for drawer menu.
 * @param mainViewModel MainViewModel
 * @param text String text for button.
 * @param icon ImageVector for icon.
 * @param navigate String for route in enum class ImcScreens.
 * @author Rodrigo Leutz
 */
@Composable
private fun DrawerMenuButton(
    mainViewModel: MainViewModel,
    text: Int,
    icon: ImageVector,
    navigate: String
) {
    var currentRoute = mainViewModel.navHostController?.currentDestination?.route
    TextButton(
        onClick = {
            mainViewModel.coroutineScope?.launch {
                mainViewModel.drawerState?.close()
                mainViewModel.navHostController?.navigate(navigate)
            }
            currentRoute = mainViewModel.navHostController?.currentDestination?.route
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = if (currentRoute == navigate) {
                colorResource(id = R.color.blue_200)
            } else {
                Color.White
            }
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = text),
                tint = if (currentRoute == navigate) {
                    Color.White
                } else {
                    Color.DarkGray
                },
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = stringResource(id = text),
                textAlign = TextAlign.Center,
                color = if (currentRoute == navigate) {
                    Color.White
                } else {
                    Color.DarkGray
                }
            )
        }
    }
}