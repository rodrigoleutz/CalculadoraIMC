package br.com.uware.calculadoraimc.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleDown
import androidx.compose.material.icons.rounded.ArrowCircleUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.model.ImcData
import java.text.SimpleDateFormat
import java.util.*


/**
 * DetailScreen
 * Composable of DetailScreen
 * @param mainViewModel MainViewModel
 * @param id UUID
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Composable
fun DetailScreen(mainViewModel: MainViewModel, id: UUID) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val imcData = remember {
        mutableStateOf<ImcData>(ImcData())
    }
    mainViewModel.getImc(id, imcData)
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            },
        elevation = 8.dp
    ) {
        val status = mainViewModel
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.imc),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "${stringResource(id = R.string.imc_weight)}: ${imcData.value.weight}"
            )
            Text(
                text = "${stringResource(id = R.string.imc_height)}: ${imcData.value.height}"
            )
            Text(
                text = "${stringResource(id = R.string.imc)}: ${imcData.value.imc}"
            )
            Text(
                text = stringResource(id = imcData.value.string),
                color = colorResource(id = imcData.value.color)
            )
            AnimatedVisibility(visible = expanded) {
                OutlinedButton(
                    onClick = {
                        mainViewModel.deleteImc(imcData.value)
                        mainViewModel.navHostController?.popBackStack()
                    }, modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.delete))
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                if (expanded) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowCircleUp,
                        contentDescription = "Arrow",
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.ArrowCircleDown,
                        contentDescription = "Arrow",
                        modifier = Modifier.align(Alignment.Bottom)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = SimpleDateFormat(
                            "dd MMMM yyyy, HH:mm:ss", Locale.getDefault()
                        ).format(imcData.value.date),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.End)
                    )
                }
            }
        }
    }
}