package com.rudnev.imreading.services

import android.content.Context
import android.util.Log
import com.rudnev.imreading.models.UrlApi
import com.rudnev.imreading.utils.TokenValidateUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class AuthService {
    private val client = OkHttpClient()

    fun login(username: String, password: String, context: Context){
        val url = "${UrlApi.url}:8000/auth/token"
        var result = false
        var token = ""

        Log.w("Debug", "Login: $username password: $password context: $context")

        val requestBody = FormBody.Builder()
            .add("username", username.toString())
            .add("password", password.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("accept", "application/json")
            .post(requestBody)
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибки при выполнении запроса
                Log.e("Debug", "Request failed: ${e.message}")
                // Дополнительная логика обработки ошибки, если необходимо
            }

            override fun onResponse(call: Call, response: Response) {
                // Обработка успешного ответа от сервера
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()

                    try {
                        val jsonObject = JSONObject(responseBody)
                        token = jsonObject.getString("access_token")
                        TokenValidateUtil.setToken(context, token)
                        result = true
                    } catch (e: JSONException) {
                        Log.e("Debug", "JSON parsing error: ${e.message}")
                        result = false
                    }
                } else {
                    // Обработка ошибочного ответа от сервера
                    Log.e("Debug", "Request failed with code: ${response.code}")
                    result = false
                }

                // Дополнительная логика в зависимости от результата аутентификации
                if (result) {
                } else {
                    // Аутентификация неудачна, выполните дополнительные действия или отобразите сообщение об ошибке
                    Log.e("Debug", "Authentication failed.")
                }
            }
        })
    }
}

/*class AuthService {
    private val client = OkHttpClient()

    suspend fun login(username: String, password: String, context: Context): Boolean {
        val url = "${UrlApi.url}:8000/auth/token"
        var result = false
        var token = ""

        Log.w("Debug", "Login: $username password: $password context: $context")

        val requestBody = FormBody.Builder()
            .add("username", username.toString())
            .add("password", password.toString())
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("accept", "application/json")
            .post(requestBody)
            .build()

        try {
            val response = client.newCall(request).execute()

            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                val jsonObject = JSONObject(responseBody)
                token = jsonObject.getString("access_token")
                TokenValidateUtil.setToken(context, token)
                result = true
            } else {
                Log.e("Debug", "Request failed with code: ${response.code}")
                result = false
            }
        } catch (e: IOException) {
            Log.e("Debug", "IOException: ${e.message}")
            result = false
        } catch (e: JSONException) {
            Log.e("Debug", "JSON parsing error: ${e.message}")
            result = false
        }

        return result
    }
}*/


    /*suspend fun login(username: String, password: String, context: Context): Boolean {
        val url = UrlApi.url + ":8000/auth/token"
        var result = false
        var token = ""

        Log.w("Debug", "Login: $username password: $password context: $context")

        val requestBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("accept", "application/json")
            .post(requestBody)
            .build()

        try {
            Log.w("Debug", "precall")

            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }

            val responseBody = response.body?.string()
            Log.w("Debug", "{$responseBody}")
            val authModel = Gson().fromJson(responseBody, AuthModel::class.java)

            result = authModel.status
            token = authModel.access_token
        } catch (e: IOException) {
            Log.w("Debug", "${e.message}")
        }

        if (result) {
            Log.w("Debug", "set token")
            TokenValidateUtil.setToken(context, token)
            Log.w("Debug", TokenValidateUtil.getToken(context).toString());
        }
        return result
    }*/