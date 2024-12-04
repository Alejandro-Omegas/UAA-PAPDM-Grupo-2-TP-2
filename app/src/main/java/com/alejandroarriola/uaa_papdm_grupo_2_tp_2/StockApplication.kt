package com.alejandroarriola.uaa_papdm_grupo_2_tp_2

import android.app.Application
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.AppContainer
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.AppDataContainer

class StockApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}