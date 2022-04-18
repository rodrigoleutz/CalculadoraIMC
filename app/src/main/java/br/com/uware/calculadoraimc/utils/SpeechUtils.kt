package br.com.uware.calculadoraimc.utils

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.MutableState
import java.util.*


/**
 * SpeechUtils
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 18 - Initial release.
 */

/**
 * intentSpeech
 * Intent to voice recognizer.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 18 - Initial release.
 */
val intentSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
}

/**
 * resultToMutableState
 * Get the data from activity result and place in MutableState.
 * @param result ActivityResult
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 18 - Initial release.
 */
fun MutableState<String>.resultToMutableState(result: ActivityResult) {
    if (result.resultCode == Activity.RESULT_OK) {
        try {
            val spokenText: String? =
                result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    .let { results ->
                        results?.get(0)
                    }
            this.value = spokenText!!
        } catch (e: Exception) {
            Log.e("SpeechUtils", e.toString())
        }
    }
}