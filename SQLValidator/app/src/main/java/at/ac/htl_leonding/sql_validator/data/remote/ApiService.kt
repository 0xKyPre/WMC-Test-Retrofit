package at.ac.htl_leonding.sql_validator.data.remote

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("post")
    suspend fun executePostQuery(
        @Body query: String
    ): ResponseBody

}