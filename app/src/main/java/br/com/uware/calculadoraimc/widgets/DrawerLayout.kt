package br.com.uware.calculadoraimc.widgets

import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import br.com.uware.calculadoraimc.MainViewModel


/**
 * DrawerLayout
 * Navigation drawer
 * @param mainViewModel MainViewModel
 * @param content { Unit } for @Composable.
 * @author Rodrigo Leutz
 */
@Composable
fun DrawerLayout(mainViewModel: MainViewModel, content: @Composable () -> Unit) {
    ModalDrawer(
        drawerState = mainViewModel.drawerState!!,
        drawerContent = {
            DrawerMenu(mainViewModel = mainViewModel)
        },
        content = {
            content()
        }
    )
}