package br.com.uware.calculadoraimc.di

import android.content.Context
import androidx.room.Room
import br.com.uware.calculadoraimc.db.ImcDatabase
import br.com.uware.calculadoraimc.db.ImcDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule
 * object module to Inject.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * provideImcDao
     * Create access to database.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Singleton
    @Provides
    fun provideImcDao(imcDatabase: ImcDatabase): ImcDatabaseDao = imcDatabase.imcDatabaseDao()

    /**
     * provideAppDatabase
     * Create a database.
     * @param context Context
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ImcDatabase =
        Room.databaseBuilder(context, ImcDatabase::class.java, "imc_database.db")
            .fallbackToDestructiveMigration().build()
}