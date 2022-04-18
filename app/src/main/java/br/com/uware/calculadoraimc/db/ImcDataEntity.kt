package br.com.uware.calculadoraimc.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.uware.calculadoraimc.R
import br.com.uware.calculadoraimc.model.ImcData
import java.util.*


/**
 * ImcDataEntity
 * Entity to save in database.
 * @param id UUID random.
 * @param weight Float of weight.
 * @param height Float of height.
 * @param date Long of timestamp.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Entity(tableName = "imc_table")
class ImcDataEntity(
    @PrimaryKey(autoGenerate = false)
    var id: UUID,
    var weight: Float,
    var height: Float,
    val date: Long
) {
    companion object {
        /**
         * fromImcData
         * Convert ImcData in ImcDataEntity
         * @param imcData ImcData
         * @return ImcDataEntity
         * @author Rodrigo Leutz
         * @version 1.0.0 - 2022 04 16 - Initial release.
         */
        fun fromImcData(imcData: ImcData) =
            ImcDataEntity(
                imcData.id,
                imcData.weight,
                imcData.height,
                imcData.date
            )
    }

    /**
     * toImcData
     * Convert ImcDataEntity in ImcData.
     * @return ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    fun toImcData() = ImcData(id, weight, height, date)
}