package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.Producto
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.StockRepository
import kotlinx.coroutines.launch
import java.text.NumberFormat

class AgregarProductoViewModel(private val stockRepository: StockRepository) : ViewModel() {
    var productoUiState by mutableStateOf(ProductoUiState())
        private set
    val longTextoCorto = 50
    val LongTextoLargo = 250
    val longCantidad = 7
    val longPrecio = 12

    fun actualizarUiState(productoDetails: ProductoDetalles) {
        productoUiState =
            ProductoUiState(productoDetalles = productoDetails, isEntryValid = validarEntrada(productoDetails))
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
                id = productoUiState.productoDetalles.id,
                nombre = productoUiState.productoDetalles.nombre,
                precio = productoUiState.productoDetalles.precio.toDouble(),
                cantidad = productoUiState.productoDetalles.cantidad.toInt(),
                detalle = productoUiState.productoDetalles.detalle
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
    val productoDetalles: ProductoDetalles = ProductoDetalles(),
    val isEntryValid: Boolean = false
)

data class ProductoDetalles(
    val id: Int = 0,
    val nombre: String = "",
    val precio: String = "",
    val cantidad: String = "",
    val detalle: String = ""
)

//Extension functions
fun ProductoDetalles.toProducto(): Producto = Producto(
    id = id,
    nombre = nombre,
    precio = precio.toDoubleOrNull() ?: 0.0,
    cantidad = cantidad.toIntOrNull() ?: 0,
    detalle = detalle
)

fun Producto.toProductoUiState(isEntryValid: Boolean = false): ProductoUiState = ProductoUiState(
    productoDetalles = this.toProductoDetalles(),
    isEntryValid = isEntryValid
)

fun Producto.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(precio)
}

fun Producto.toProductoDetalles(): ProductoDetalles = ProductoDetalles(
    id = id,
    nombre = nombre,
    precio = precio.toString(),
    cantidad = cantidad.toString(),
    detalle = detalle ?: "" //si detalle esta null, sera un string vacio por default
)