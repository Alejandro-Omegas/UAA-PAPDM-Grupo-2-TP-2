package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.StockApplication
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.AgregarProductoViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.ListaStockViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ListaStockViewModel(stockApplication().container.stockRepository)
        }

        initializer {
            AgregarProductoViewModel(stockApplication().container.stockRepository)
        }

        /**TODO
         * Agregar initializaer de EditarProductoViewModel y DetalleProductoViewModel
         * una vez esten implementados
         */
    }
}

fun CreationExtras.stockApplication(): StockApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StockApplication)