package br.com.uware.calculadoraimc.db

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * ImcDatabase
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@Database(entities = [ImcDataEntity::class], version = 1, exportSchema = false)
abstract class ImcDatabase: RoomDatabase() {
    abstract fun imcDatabaseDao(): ImcDatabaseDao
}