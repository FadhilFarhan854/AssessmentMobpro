package org.d3if0739.assessment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.model.Keuangan
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "indraazimi/mobpro1-compose/static-api/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()
interface KeuanganApiService {
    @GET("static-api.json")
    suspend fun getHewan(): List<Hewan>
}

object KeuanganApi {
    val service: KeuanganApiService by lazy {
        retrofit.create(KeuanganApiService::class.java)
    }
    fun getHewanUrl(imageId:String): String {
        return "$BASE_URL$imageId.jpg"
    }
}
enum class ApiStatus{LOADING, SUCCESS, FAILED}