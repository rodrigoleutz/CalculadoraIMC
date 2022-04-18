package br.com.uware.calculadoraimc.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*


/**
 * ImcDatabaseDao
 * Database access.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Dao
interface ImcDatabaseDao {
    /**
     * getAllImcData
     * Get all saved data
     * @return Flow<List<ImcDataEntity>>
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Query("SELECT * from imc_table")
    fun getAllImcData(): Flow<List<ImcDataEntity>>

    /**
     * getImcData
     * Get single ImcData in database.
     * @param id UUID
     * @return ImcDataEntity
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Query("SELECT * from imc_table WHERE id = :id")
    suspend fun getImcData(id: UUID): ImcDataEntity

    /**
     * insert
     * Add data to datatbase
     * @param imcDataEntity ImcDataEntity
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imcDataEntity: ImcDataEntity)

    /**
     * update
     * Update data in database.
     * @param imcDataEntity ImcDataEntity
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(imcDataEntity: ImcDataEntity)

    /**
     * delete
     * Delete single data in database
     * @param imcDataEntity ImcDataEntity
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Delete
    suspend fun delete(imcDataEntity: ImcDataEntity)

    /**
     * deleteAll
     * Delete all data in database.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Query("DELETE from imc_table")
    suspend fun deleteAll()
}