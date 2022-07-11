package br.com.uware.calculadoraimc

import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.com.uware.calculadoraimc.db.ImcDataEntity
import br.com.uware.calculadoraimc.model.ImcData
import br.com.uware.calculadoraimc.repository.ImcDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


/**
 * MainViewModel
 * MainViewModel of app.
 * @param repository Inject repository.
 * @author Rodrigo Leutz
 * @version 1.0.0 - 2022 04 16 - Initial release.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ImcDataRepository) :
    ViewModel() {

    // NavHostController
    var navHostController: NavHostController? = null

    // Navigation Drawer State
    var drawerState: DrawerState? = null

    // CoroutineScope
    var coroutineScope: CoroutineScope? = null

    // Saved List
    private val _imcList = MutableStateFlow<List<ImcDataEntity>>(emptyList())
    val imcList = _imcList.asStateFlow()

    /**
     * init
     * Init Unit to load all ImcDataEntity.
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release.
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().distinctUntilChanged().collect {
                if (it.isNotEmpty()) {
                    _imcList.value = it
                }
            }
        }
    }

    /**
     * addImc
     * Save in database ImcData
     * @param imcData ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release
     */
    fun addImc(imcData: ImcData) = viewModelScope.launch {
        try {
            repository.add(imcData)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    /**
     * getImc
     * Get from database ImcData and store in mutableState
     * @param id UUID
     * @param imcData MutableState of ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release
     */
    fun getImc(id: UUID, imcData: MutableState<ImcData>) = viewModelScope.launch {
        try {
            imcData.value = repository.get(id)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    /**
     * deleteImc
     * Delete ImcData in database.
     * @param imcData ImcData
     * @author Rodrigo Leutz
     * @version 1.0.0 - 2022 04 16 - Initial release
     */
    fun deleteImc(imcData: ImcData) = viewModelScope.launch {
        try {
            repository.delete(imcData)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    companion object {
        // TAG for log.
        private const val TAG = "MainViewModel"
    }

}