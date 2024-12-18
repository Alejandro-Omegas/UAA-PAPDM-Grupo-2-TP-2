package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DetalleProductoUiState(
    val agotado: Boolean = true,
    val productoDetalles: ProductoDetalles = ProductoDetalles()
)

class DetalleProductoViewModel(
    savedStateHandle: SavedStateHandle,
    private val stockRepository: StockRepository
) : ViewModel() {
    private val productoId: Int = checkNotNull(savedStateHandle[DetalleProductoDestino.idProductoArg]) //explicitamante declarar como Int o el IDE lo identifica como "Any" type
    val uiState: StateFlow<DetalleProductoUiState> =
        stockRepository.obtenerStockPorId(productoId)
            .filterNotNull()
            .map {
                DetalleProductoUiState(agotado = it.cantidad <= 0, productoDetalles = it.toProductoDetalles())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetalleProductoUiState()
            )

    //funciones
    suspend fun eliminarProducto() {
        try {
            stockRepository.eliminarStock(uiState.value.productoDetalles.toProducto())
        } catch (e: Exception) {
            Log.e("Error al eliminar producto", e.toString(), e)
        }
    }

    fun reducirUno() {
        viewModelScope.launch {
            val productoActual = uiState.value.productoDetalles.toProducto()

            if(productoActual.cantidad > 0) {
                try {
                    stockRepository.actualizarStock(productoActual.copy(cantidad = productoActual.cantidad - 1))
                } catch (e: Exception) {
                    Log.e("Error al reducir cantidad", e.toString(), e)
                }
            }
        }
    }

    fun aumentarUno() {
        viewModelScope.launch {
            val productoActual = uiState.value.productoDetalles.toProducto()

            if(productoActual.cantidad < 2147483647) { //max Int value
                try {
                    stockRepository.actualizarStock(productoActual.copy(cantidad = productoActual.cantidad + 1))
                } catch (e: Exception) {
                    Log.e("Error al aumentar cantidad", e.toString(), e)
                }
            }
        }
    }

    //singletons
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
