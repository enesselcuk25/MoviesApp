package com.enes.moviesapp.data.local.service

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromInt(value:String?):List<Int>{
        val listType = object :TypeToken<List<Int>>(){}.type
        return  Gson().fromJson(value,listType)
    }
    @TypeConverter
    fun fromList(list: List<Int>?):String{
        val listType = object : TypeToken<List<Int>>(){}.type
        return Gson().toJson(list,listType)
    }

}