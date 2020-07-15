@file:Suppress("DEPRECATION")

package com.example.newquiz

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.google.okhttp.OkHttpConnection
//import com.google.okhttp.OkHttpsConnection
import kotlinx.android.synthetic.main.activity_science_quiz.*
import okhttp3.OkHttpClient
import okhttp3.Request

@Suppress("DEPRECATION")
class ScienceQuiz : AppCompatActivity() {

    lateinit var context : Context
    var hasInternet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_quiz)
        context = this
        getQuestions().execute()
    }

    internal inner class getQuestions : AsyncTask<Void,Void,String>(){

        lateinit var progressDialog : ProgressDialog
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Downloading Question...")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }
        override fun doInBackground(vararg p0: Void?): String {
           if (isNetworkAvailable()){
               hasInternet = true
               val client = OkHttpClient()
               val url = "https://opentdb.com/api.php?amount=10"
               val request = Request.Builder().url(url).build()
               val response = client.newCall(request).execute()
               return response.body()?.string().toString()
           }else{
               return ""
           }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()

            if (hasInternet){
                tv_result.text = result
            }else{
                tv_result.text = "No Internet"
            }
        }

    }
    
    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected

    }
}