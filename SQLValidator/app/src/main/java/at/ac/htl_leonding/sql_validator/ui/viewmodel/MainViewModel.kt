package at.ac.htl_leonding.sql_validator.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.htl_leonding.sql_validator.data.local.SQLLogEntry
import at.ac.htl_leonding.sql_validator.data.repository.AppRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AppRepository) : ViewModel() {

    var queryInput by mutableStateOf("Your SQL Query")
    var apiResponseResult by mutableStateOf("No Request sent")

    val dbLogs: StateFlow<List<SQLLogEntry>> = repository.allLogs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun triggerPostSQLQuery() {
        viewModelScope.launch {
            apiResponseResult = "Send POST..."
            apiResponseResult = repository.executePostRequest(queryInput)
        }
    }
}