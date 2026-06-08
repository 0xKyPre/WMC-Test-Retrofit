package at.ac.htl_leonding.sql_validator.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SQLLogDao {
    @Insert
    suspend fun insertLog(log: SQLLogEntry)

    @Query("SELECT * FROM sql_logs ORDER BY timeStamp DESC")
    fun getAllLogs(): Flow<List<SQLLogEntry>>

    @Query("SELECT * FROM sql_logs WHERE `query` = :userQuery")
    suspend fun getQueryByQuery(userQuery: String): List<SQLLogEntry>
}