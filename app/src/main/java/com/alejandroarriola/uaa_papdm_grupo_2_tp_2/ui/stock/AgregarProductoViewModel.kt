package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository

class AgregarProductoViewModel(private val stockRepository: StockRepository) : ViewModel() {
    var productoUiState by mutableStateOf(ProductoUiState())
        private set

    fun actualizarUiState(productoDetails: ProductoDetalles) {
        productoUiState =
            ProductoUiState(productoDetails = productoDetails, isEntryValid = validarEntrada(productoDetails))
    }

    private fun validarEntrada(productoDetails: ProductoDetalles): Boolean {
        return with(productoDetails) {
            nombre.isNotBlank() && precio.isNotBlank() && cantidad.isNotBlank()
        }
    }

   // implementa la función agregarProducto
    fun agregarProducto() {
        if (productoUiState.isEntryValid) {
            val producto = Producto(
                id = productoUiState.productoDetails.id,
                nombre = productoUiState.productoDetails.nombre,
                precio = productoUiState.productoDetails.precio.toDouble(),
                cantidad = productoUiState.productoDetails.cantidad.toInt(),
                descripcion = productoUiState.productoDetails.descripcion
            )
            
             // llama al repositorio para agregar el producto
    //viewModelScope.launch para realizar la operación de base de datos, sin bloquear la UI
            viewModelScope.launch {
                stockRepository.agregarStock(producto)
            }
        }
    }
}
            

data class ProductoUiState(
    val productoDetails: ProductoDetalles = ProductoDetalles(),
    val isEntryValid: Boolean = false
)

data class ProductoDetalles(
    val id: Int = 0,
    val nombre: String = "",
    val precio: String = "",
    val cantidad: String = "",
    val descripcion: String = ""
)
