package br.com.uware.calculadoraimc.model

import br.com.uware.calculadoraimc.R
import java.util.*

/**
 * ImcData
 * Model of ImcData
 * @param id UUID = random
 * @param weight Float of weight.
 * @param height Float of height.
 * @param date Log of timestamp.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
data class ImcData(
    var id: UUID = UUID.randomUUID(),
    var weight: Float = 0f,
    var height: Float = 0f,
    val date: Long = System.currentTimeMillis()
) {
    /**
     * imc
     * Get imc calc.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    val imc: String
        get() = "%.2f".format(weight / (height * height))

    /**
     * arrayStrings
     * Array List for string resource id.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    private val arrayStrings = arrayListOf<Int>(
        R.string.imc_string_1,
        R.string.imc_string_2,
        R.string.imc_string_3,
        R.string.imc_string_4,
        R.string.imc_string_5,
        R.string.imc_string_6,
        R.string.imc_string_7
    )

    /**
     * string
     * Get imc string resource id.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    val string: Int = arrayStrings[imcPosition()]

    /**
     * arrayColors
     * ArrayList for color resource id.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    private val arrayColors = arrayListOf<Int>(
        R.color.blue_700,
        R.color.blue_500,
        R.color.green_500,
        R.color.purple_200,
        R.color.purple_500,
        R.color.purple_700,
        R.color.purple_700
    )

    /**
     * color
     * Get imc color resource id.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    val color: Int = arrayColors[imcPosition()]

    /**
     * imcPosition
     * Get position in array for imc.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    private fun imcPosition(): Int {
        return when (imc.replace(",", ".").toFloat()) {
            in 0.0..17.09 -> 0
            in 17.1..18.49 -> 1
            in 18.5..24.99 -> 2
            in 25.0..29.99 -> 3
            in 30.0..34.99 -> 4
            in 35.0..39.99 -> 5
            else -> 6
        }
    }

    /**
     * water
     * Calc for need drink water per day.
     * @author Rodrigo Leutz and Cleverson Baron
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    val water: Float
        get() = (weight * 35) / 1000

}