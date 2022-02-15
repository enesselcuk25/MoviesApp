package com.enes.moviesapp.data.local.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enes.moviesapp.data.local.entity.MovieFavoriteEntity


@Database(entities =[MovieFavoriteEntity::class], version = 1, exportSchema = false )

@TypeConverters(Converters::class)
abstract class MoviesDataBase:RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object{
        @Volatile
        private var INSTANCE: MoviesDataBase? = null

        fun getDatabase(context: Context): MoviesDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDataBase::class.java,"movies_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}