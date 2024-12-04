package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class StockDatabase: RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object{
        @Volatile //provides visibility throughout all the threads, making it always up-to-date
        private var INSTANCE: StockDatabase? = null

        fun getDatabase(context: Context): StockDatabase {
                return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context, //option: context.applicationContext
                    StockDatabase::class.java,
                    "stock_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}