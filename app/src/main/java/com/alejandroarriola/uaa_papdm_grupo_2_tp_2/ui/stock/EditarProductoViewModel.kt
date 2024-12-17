import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class DetalleProductoViewModel(
    savedStateHandle: SavedStateHandle,
    private val stockViewModel: ListaStockViewModel
) : ViewModel() {
    private val productoId: Int = savedStateHandle[DetalleProductoDestino.idProductoArg]?.toInt() ?: 0

    private val _uiState = MutableStateFlow(ProductoUiState())
    val uiState: StateFlow<ProductoUiState> = _uiState

    init {
        cargarProducto()
    }

    private fun cargarProducto() {
        val producto = stockViewModel.obtenerProductoPorId(productoId)
        _uiState.update { currentState ->
            currentState.copy(
                nombre = producto?.nombre ?: "",
                descripcion = producto?.descripcion ?: "",
                precio = producto?.precio ?: 0.0
            )
        }
    }

    suspend fun eliminarProducto() {
        try {
            stockViewModel.eliminarProducto(productoId)
            // Aquí podrías actualizar el estado si deseas mostrar un mensaje de éxito
        } catch (e: Exception) {
            // Manejo de errores, si el proceso de eliminación falla
            _uiState.update { currentState ->
                currentState.copy(
                    mensajeError = "Error al eliminar el producto: ${e.message}"
                )
            }
        }
    }
}

data class ProductoUiState(
    val nombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val mensajeError: String? = null // Nuevo campo para manejar mensajes de error
)
