package com.example.internettest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Button
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url ="https://https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

        findViewById<Button>(R.id.btnHTTP).setOnClickListener{
            val json = URL(url)
            Thread {
                try{
                    val connection = json.openConnection() as HttpURLConnection
                    val data = connection.inputStream.bufferedReader().readText()
                    connection.disconnect()
                    Log.d("Flickr Cats", data);
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }.start()
        }

        findViewById<Button>(R.id.btnOkHTTP).setOnClickListener{

        }
    }

    private fun getAsyncCall(url: String){
        val httpClient = OkHttpClient()
        val url = "http://www.zoftino.com/api/storeOffers"
        val request: Request = Builder().url(url).build()

        httpClient.newCall(request).enqueue(object : Callback {
            fun onFailure(call: Call?, e: IOException?) {
                Log.e(TAG, "error in getting response using async okhttp call")
            }

            @Throws(IOException::class)
            fun onResponse(call: Call?, response: Response) {
                val responseBody: ResponseBody = response.body()
                if (!response.isSuccessful()) {
                    throw IOException("Error response $response")
                }
                Log.i(TAG, responseBody.string())
            }
        })
    }
}