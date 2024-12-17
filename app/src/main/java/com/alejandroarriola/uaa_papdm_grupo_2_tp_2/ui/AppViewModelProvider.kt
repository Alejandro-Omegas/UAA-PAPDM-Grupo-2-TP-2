package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.StockApplication
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.AgregarProductoViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.DetalleProductoViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.EditarProductoViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.ListaStockViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ListaStockViewModel(stockApplication().container.stockRepository)
        }

        initializer {
            AgregarProductoViewModel(stockApplication().container.stockRepository)
        }

        initializer {
            DetalleProductoViewModel(
                this.createSavedStateHandle(),
                stockApplication().container.stockRepository
            )
        }

        initializer {
            EditarProductoViewModel(
                this.createSavedStateHandle(),
                stockApplication().container.stockRepository
            )
        }
    }
}

fun CreationExtras.stockApplication(): StockApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as StockApplication)
