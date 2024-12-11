package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository

data class DetalleProductoUiState(
    val agotado: Boolean = true,
    val productoDetalles: ProductoDetalles = ProductoDetalles()
)

class DetalleProductoViewModel(
    savedStateHandle: SavedStateHandle,
    private val stockRepository: StockRepository
) : ViewModel() {
    /*TODO:
    private val productoId

    val uiState

    suspend fun eliminarProducto()

    fun aumentarUno()

    fun reducirUno()
     */
}