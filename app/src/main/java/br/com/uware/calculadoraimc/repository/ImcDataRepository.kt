package br.com.uware.calculadoraimc.repository

import br.com.uware.calculadoraimc.db.ImcDataEntity
import br.com.uware.calculadoraimc.db.ImcDatabaseDao
import br.com.uware.calculadoraimc.model.ImcData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject


/**
 * ImcDataRepository
 * Repository to access database.
 * @param imcDatabaseDao ImcDatabaseDao by inject.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
class ImcDataRepository @Inject constructor(private val imcDatabaseDao: ImcDatabaseDao) {
    /**
     * add
     * Add ImcData and convert to ImcDataEntity to save in database.
     * @param imcData ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    suspend fun add(imcData: ImcData) =
        imcDatabaseDao.insert(ImcDataEntity.fromImcData(imcData))

    /**
     * update
     * Update ImcDataEntity from ImcData
     * @param imcData ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    suspend fun update(imcData: ImcData) =
        imcDatabaseDao.update(ImcDataEntity.fromImcData(imcData))

    /**
     * delete
     * Delete ImcDataEntity
     * @param imcData ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    suspend fun delete(imcData: ImcData) =
        imcDatabaseDao.delete(ImcDataEntity.fromImcData(imcData))

    /**
     * deleteAll
     * Delete all ImcDataEntity from database.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    suspend fun deleteAll() = imcDatabaseDao.deleteAll()

    /**
     * get
     * Get ImcData from database.
     * @param id UUID
     * @return ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    suspend fun get(id: UUID): ImcData =
        imcDatabaseDao.getImcData(id).toImcData()

    /**
     * getAll
     * Get all ImcDataEntity from database.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    fun getAll(): Flow<List<ImcDataEntity>> =
        imcDatabaseDao.getAllImcData().flowOn(Dispatchers.IO).conflate()
}