package at.ac.htl_leonding.sql_validator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sql_logs")
data class SQLLogEntry (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val query: String,
    val amountOfErrors: Int,

    val timeStamp: Long = System.currentTimeMillis()
)