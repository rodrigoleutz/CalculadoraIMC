package br.com.uware.calculadoraimc.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import br.com.uware.calculadoraimc.R


/**
 * InputTextField
 * EditText for Jetpack Compose.
 * @param modifier Modifier
 * @param valueState MutableState<String> for value
 * @param text String for text in InputField
 * @param trailingIcon { Unit } for trailing icon = { }.
 * @param enable Boolean = true
 * @param isSingleLine Boolean for single line = true.
 * @param keyboardType KeyboardType = Number
 * @param imeAction = ImeAction for keyboard = Next.
 * @param onAction KeyboardAction = Default.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    text: String,
    trailingIcon: @Composable () -> Unit = {},
    enable: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = valueState.value, onValueChange = { valueState.value = it },
        label = { Text(text = text) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        trailingIcon = { trailingIcon() },
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.blue_500),
            unfocusedBorderColor = colorResource(id = R.color.blue_500),
            focusedLabelColor = colorResource(id =R.color.blue_500),
            cursorColor = colorResource(id = R.color.blue_500)
        )
    )
}