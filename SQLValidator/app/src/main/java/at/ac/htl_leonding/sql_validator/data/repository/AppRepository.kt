package at.ac.htl_leonding.sql_validator.data.repository

import at.ac.htl_leonding.sql_validator.data.local.SQLLogDao
import at.ac.htl_leonding.sql_validator.data.local.SQLLogEntry
import at.ac.htl_leonding.sql_validator.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val apiService: ApiService,
    private val logDao: SQLLogDao
) {
    val allLogs: Flow<List<SQLLogEntry>> = logDao.getAllLogs()

    suspend fun executePostRequest(query:String): String {
        return try {
            val response = apiService.executePostQuery(query)
            val responseString = response.string()

            logDao.insertLog(SQLLogEntry(query = query, amountOfErrors = 0))

            "POST Success: $responseString"
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}