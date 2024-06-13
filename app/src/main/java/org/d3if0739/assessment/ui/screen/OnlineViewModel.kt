package org.d3if0739.assessment.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.network.ApiStatus
import org.d3if0739.assessment.network.KeuanganApi
import java.io.ByteArrayOutputStream

class OnlineViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Hewan>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private  set

    var errorMessage = mutableStateOf<String?>(null)
        private  set

    fun saveData(userId: String, nama: String, namaLatin: String, jenis: String, bitmap: Bitmap, mine: String){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val result = KeuanganApi.service.postHewan(
                    userId,
                    nama.toRequestBody("text/plain".toMediaTypeOrNull()),
                    namaLatin.toRequestBody("text/plain".toMediaTypeOrNull()),
                    jenis.toRequestBody("text/plain".toMediaTypeOrNull()),

                    bitmap.toMultipartBody(),
                    mine.toRequestBody("text/plain".toMediaTypeOrNull())
                )
                if (result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            }catch (e: Exception){
                Log.d("OnlineViewMOdel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    fun retrieveData(userId: String){
        viewModelScope.launch(Dispatchers.IO){
            status.value = ApiStatus.LOADING
            try {
                data.value = KeuanganApi.service.getHewan(userId)
                status.value = ApiStatus.SUCCESS
            }catch (e: Exception){
                Log.d("OnlineViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(),0,byteArray.size
        )
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody
        )
    }
    fun deleteData(userId: String, id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = KeuanganApi.service.deleteKeuangan(userId, id)
                if (result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    fun clearMessage(){errorMessage.value = null}

}