package br.com.uware.calculadoraimc.screens.imc

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.uware.calculadoraimc.MainViewModel
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.components.InputTextField
import br.com.uware.calculadoraimc.model.ImcData
import br.com.uware.calculadoraimc.utils.intentSpeech
import br.com.uware.calculadoraimc.utils.resultToMutableState


/**
 * ImcScreen
 * Create Imc screen.
 * @param mainViewModel MainViewModel
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImcScreen(mainViewModel: MainViewModel) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var saved by remember {
        mutableStateOf(false)
    }
    val weight = remember {
        mutableStateOf("")
    }
    val height = remember {
        mutableStateOf("")
    }
    val result = remember {
        mutableStateOf<ImcData>(ImcData())
    }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val imcCalculateErrorString = stringResource(id = R.string.imc_calculate_error)
    val imcCalculateEmptyString = stringResource(id = R.string.imc_calculate_empty)
    val startForWeight =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            weight.resultToMutableState(it)
        }
    val startForHeight =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            height.resultToMutableState(it)
        }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Calculate,
                        contentDescription = stringResource(id = R.string.imc_calculator),
                        tint = Color.DarkGray,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.imc_calculator),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
                InputTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    valueState = weight,
                    text = stringResource(id = R.string.imc_weight),
                    trailingIcon = {
                        IconButton(onClick = {
                            startForWeight.launch(intentSpeech)
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Mic,
                                contentDescription = stringResource(
                                    id = R.string.mic
                                )
                            )
                        }
                    }
                )
                InputTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    valueState = height,
                    text = stringResource(id = R.string.imc_height),
                    imeAction = ImeAction.Done,
                    onAction = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            startForHeight.launch(intentSpeech)
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Mic,
                                contentDescription = stringResource(
                                    id = R.string.mic
                                )
                            )
                        }
                    }
                )
                AnimatedVisibility(visible = expanded) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.imc),
                            style = MaterialTheme.typography.h4,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Imc: ${result.value.imc}",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = stringResource(id = result.value.string),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = colorResource(id = result.value.color),
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = stringResource(
                                id = R.string.need_drink,
                                result.value.water
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.caption
                        )
                        OutlinedButton(
                            onClick = {
                                mainViewModel.addImc(result.value)
                                saved = true
                            },
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                                .fillMaxWidth(),
                            enabled = !saved
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                color = if (saved) Color.DarkGray
                                else colorResource(id = R.color.blue_500)
                            )
                        }
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    onClick = {
                        if (expanded) {
                            result.value = ImcData()
                            weight.value = ""
                            height.value = ""
                            saved = false
                            expanded = !expanded
                        } else {
                            try {
                                if (weight.value.isNotBlank() && height.value.isNotBlank()) {
                                    result.value = ImcData(
                                        weight = weight.value.toFloat(),
                                        height = height.value.toFloat()
                                    )
                                    expanded = !expanded
                                } else {
                                    Toast.makeText(
                                        context,
                                        imcCalculateEmptyString,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: Exception) {
                                Toast.makeText(context, imcCalculateErrorString, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        focusManager.clearFocus()
                    },
                    colors = if (expanded) {
                        ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Red
                        )
                    } else {
                        ButtonDefaults.outlinedButtonColors(
                            contentColor = colorResource(id = R.color.green_500)
                        )
                    }
                ) {
                    if (expanded) {
                        Text(
                            text = stringResource(id = R.string.clean),
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.calculate),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}