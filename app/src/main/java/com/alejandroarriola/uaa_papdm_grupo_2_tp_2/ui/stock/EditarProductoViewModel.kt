package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.util.Log
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
            try{
                productoUiState = stockRepository.obtenerStockPorId(productoId)
                    .filterNotNull()
                    .first()
                    .toProductoUiState(true)
            } catch (e: Exception) {
                Log.e("Error al obtener el producto en EditarProductoViewModel", e.toString(), e)
            }
        }
    }

    suspend fun actualizarProducto() {
        if (validarEntrada(productoUiState.productoDetalles)) {
            try{
                stockRepository.actualizarStock(productoUiState.productoDetalles.toProducto())
            } catch (e: Exception) {
                Log.e("Error al actualizar el producto en EditarProductoViewModel", e.toString(), e)
            }
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
