package org.d3if0739.assessment.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.model.Keuangan
import org.d3if0739.assessment.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


private const val BASE_URL = "https://cashapis.online/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL).build()
interface KeuanganApiService {
    @GET("json.php")
    suspend fun getHewan(
        @Query("auth") userId: String
    ): List<Hewan>

    @Multipart
    @POST("json.php")
    suspend fun  postHewan(
        @Part("auth") userId : String,
        @Part("tanggal") tanggal: RequestBody,
        @Part("jumlah") jumlah: RequestBody,
        @Part("jenis") jenis: RequestBody,
        @Part image: MultipartBody.Part,
        @Part ("mine") mine : RequestBody
    ): OpStatus

    @DELETE("json.php")
    suspend fun deleteKeuangan(
        @Header("Authorization") userId: String,
        @Query("id") id: Long
    ): OpStatus
}

object KeuanganApi {
    val service: KeuanganApiService by lazy {
        retrofit.create(KeuanganApiService::class.java)
    }
    fun getHewanUrl(imageId:String): String {
        return "$BASE_URL$imageId"
    }
}
enum class ApiStatus{LOADING, SUCCESS, FAILED}