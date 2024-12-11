package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.Producto
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


data class ListaStockUiState(
    val listaProductos: List<Producto> = listOf()
)

class ListaStockViewModel(stockRepository: StockRepository): ViewModel() {
    val listaStockUiState: StateFlow<ListaStockUiState> = stockRepository.obtenerStockTodo().map { ListaStockUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ListaStockUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
