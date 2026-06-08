package at.ac.htl_leonding.sql_validator.data.repository

import at.ac.htl_leonding.sql_validator.data.local.SQLLogDao
import at.ac.htl_leonding.sql_validator.data.local.SQLLogEntry
import at.ac.htl_leonding.sql_validator.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import org.json.JSONArray
import org.json.JSONException

class AppRepository(
    private val apiService: ApiService,
    private val logDao: SQLLogDao
) {
    val allLogs: Flow<List<SQLLogEntry>> = logDao.getAllLogs()

    suspend fun executePostRequest(query:String): String {
        return try {
            val queriesFromDatabase = logDao.getQueryByQuery(query);
            var loadFromString = "DB"

            val response = apiService.executePostQuery(query)
            var responseString = response.string()
            var amountOfError = 0;

            try {
                val size = JSONArray(responseString).length();
                responseString = "Amount of Error: $size";
                amountOfError = size;

                if(amountOfError == 0) {
                    responseString = "No Errors in SQL Query"
                }
            } catch (e: JSONException) {
                responseString = "JSON ERROR";
            }

            if (queriesFromDatabase.isEmpty()) {
                loadFromString = "WEB"

                logDao.insertLog(SQLLogEntry(query = query, amountOfErrors = amountOfError))
            }

            "Query POST Success: $responseString and load from $loadFromString"
        } catch (e: Exception) {
            "Error: ${e.localizedMessage}"
        }
    }
}