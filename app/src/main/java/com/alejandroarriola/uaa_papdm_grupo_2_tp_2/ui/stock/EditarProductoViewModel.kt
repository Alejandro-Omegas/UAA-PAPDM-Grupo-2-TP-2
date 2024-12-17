package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditarProductoViewModel(
    savedStateHandle: SavedStateHandle,
    private val stockRepository: StockRepository
) : ViewModel() {
    var productoUiState by mutableStateOf(ProductoUiState())
        private set

    private val productoId: Int = checkNotNull(savedStateHandle[DetalleProductoDestino.idProductoArg])

    init {
        viewModelScope.launch {
            productoUiState = stockRepository.obtenerStockPorId(productoId)
                .filterNotNull()
                .first()
                .toProductoUiState(true)
        }
    }

    suspend fun actualizarProducto() {
        if (validarEntrada(productoUiState.productoDetalles)) {
            stockRepository.actualizarStock(productoUiState.productoDetalles.toProducto())
        }
    }

    private fun validarEntrada(uiState: ProductoDetalles = productoUiState.productoDetalles): Boolean {
        return with(uiState) {
            nombre.isNotBlank() && precio.isNotBlank() && cantidad.isNotBlank()
        }
    }

    fun actualizarUiState(productoDetalles: ProductoDetalles) {
        productoUiState =
            ProductoUiState(productoDetalles = productoDetalles, isEntryValid = validarEntrada(productoDetalles))
    }
}
