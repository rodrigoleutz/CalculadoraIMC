package br.com.uware.calculadoraimc.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.navigation.ImcScreens


/**
 * HomeScreen
 * App home screen
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 */
@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HomeButton(
            R.string.imc_calculator,
            Icons.Rounded.Calculate,
            ImcScreens.ImcScreen.name,
            mainViewModel
        )
        HomeButton(
            text = R.string.saved_screen,
            icon = Icons.Rounded.Save,
            navigation = ImcScreens.SavedScreen.name,
            mainViewModel = mainViewModel
        )
    }
}

/**
 * HomeButton
 * Button for home screen
 * @param text Int of text and description
 * @param icon ImageVector of icon
 * @param navigation String in enum class ImcScreens
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 */
@Composable
private fun HomeButton(
    text: Int,
    icon: ImageVector,
    navigation: String,
    mainViewModel: MainViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                mainViewModel.navHostController?.navigate(navigation)
            },
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = text),
                modifier = Modifier.padding(end = 8.dp),
                tint = Color.DarkGray
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }
}