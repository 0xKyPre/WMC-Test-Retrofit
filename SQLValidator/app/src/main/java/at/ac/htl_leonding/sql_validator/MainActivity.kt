package at.ac.htl_leonding.sql_validator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import at.ac.htl_leonding.sql_validator.data.local.AppDatabase
import at.ac.htl_leonding.sql_validator.data.remote.ApiService
import at.ac.htl_leonding.sql_validator.data.repository.AppRepository
import at.ac.htl_leonding.sql_validator.ui.screens.MainScreen
import at.ac.htl_leonding.sql_validator.ui.theme.SQLValidatorTheme
import at.ac.htl_leonding.sql_validator.ui.viewmodel.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dataBase = AppDatabase.getDatabase(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://softwium.com/sql-validator/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val repository = AppRepository(apiService, dataBase.logDao())

        val viewModel = MainViewModel(repository)

        setContent {
            SQLValidatorTheme {
                Surface {
                    MainScreen(viewModel)
                }
            }
        }
    }
}