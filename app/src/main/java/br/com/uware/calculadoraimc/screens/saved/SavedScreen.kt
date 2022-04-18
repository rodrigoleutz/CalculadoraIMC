package br.com.uware.calculadoraimc.screens.saved

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.model.ImcData
import br.com.uware.calculadoraimc.navigation.ImcScreens
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


/**
 * SavedScreen
 * Saved items in database screen.
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 */
@Composable
fun SavedScreen(mainViewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        LazyColumn {
            items(mainViewModel.imcList.value) { imcData ->
                SavedItem(imcData = imcData.toImcData()) {
                    mainViewModel.navHostController?.navigate(
                        ImcScreens.DetailScreen.name + "/${imcData.id}"
                    )
                }
            }
        }
    }
}

/**
 * SavedItem
 * Item compose for ImcData in SavedScreen
 * @param imcData ImcData
 * @param onClick Unit for click
 * @author Rodrigo Leutz
 */
@Composable
fun SavedItem(imcData: ImcData, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "IMC: ${imcData.imc}")
            Text(
                text = stringResource(id = imcData.string),
                color = colorResource(id = imcData.color)
            )
            Text(
                text = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
                    .format(imcData.date),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedItemPreview() {
    val imcData = ImcData()
    SavedItem(imcData = imcData)
}