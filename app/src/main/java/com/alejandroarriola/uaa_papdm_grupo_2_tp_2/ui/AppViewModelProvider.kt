package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.StockApplication
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.AgregarProductoViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AgregarProductoViewModel(StockApplication().container.stockRepository)
        }
    }
}