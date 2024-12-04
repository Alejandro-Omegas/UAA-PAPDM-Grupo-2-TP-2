package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data

import android.content.Context

interface AppContainer {
    val stockRepository: StockRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val stockRepository: StockRepository by lazy {
        LocalRepository(StockDatabase.getDatabase(context).stockDao())
    }
}