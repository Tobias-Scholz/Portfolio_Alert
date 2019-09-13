package com.example.portfolio_alert

import android.content.Context
import android.util.Log.d
import org.json.JSONObject
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception

fun writeToFile(context: Context, fileName: String, jsonString: String){
    try {
        val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)

        fos.write(jsonString.toByteArray())

        fos.close()
    } catch (e: Exception){
        e.printStackTrace()
    }
}

fun readFromFile(context: Context, fileName: String) : JSONObject{
    try {
        val fis = InputStreamReader(context.assets.open(fileName))
        val content = fis.readText()
        d("Tobias", content)
    } catch (e: Exception){
        e.printStackTrace()
    }
    return JSONObject()
}