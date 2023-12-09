package com.rudnev.imreading.services

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.rudnev.imreading.models.UrlApi
import com.rudnev.imreading.models.User
import com.rudnev.imreading.models.UserResponse
import com.rudnev.imreading.utils.TokenValidateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class ProfileService(private val context: Context) {
    private val client = OkHttpClient()

    suspend fun get(): User? {
        return withContext(Dispatchers.IO) {
            val url = UrlApi.url + ":8004/users/me"

            Log.w("Debug", TokenValidateUtil.getToken(context).toString());

            val request = Request.Builder()
                .url(url)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + TokenValidateUtil.getToken(context))
                .get()
                .build()

            try {
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    Log.w("Debug", "$responseBody")
                    val userResponse = Gson().fromJson(responseBody, UserResponse::class.java)
                    return@withContext userResponse.user
                } else {
                    Log.w("Debug", "Unsuccessful response: ${response.code}")
                }
            } catch (e: IOException) {
                Log.w("Debug", "IOException: ${e.message}")
            } finally {
                client.dispatcher.executorService.shutdown()
            }

            return@withContext null
        }
    }
}
