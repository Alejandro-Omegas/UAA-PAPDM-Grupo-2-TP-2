import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ProductoUiState(
    val nombre: String = "",
    val precio: String = "",
    val cantidad: String = "",
    val detalle: String = ""
)

class EditarProductoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProductoUiState())
    val uiState: StateFlow<ProductoUiState> = _uiState

    fun onNombreChange(newNombre: String) {
        _uiState.value = _uiState.value.copy(nombre = newNombre)
    }

    fun onPrecioChange(newPrecio: String) {
        _uiState.value = _uiState.value.copy(precio = newPrecio)
    }

    fun onCantidadChange(newCantidad: String) {
        _uiState.value = _uiState.value.copy(cantidad = newCantidad)
    }

    fun onDetalleChange(newDetalle: String) {
        _uiState.value = _uiState.value.copy(detalle = newDetalle)
    }

    fun actualizarProducto(onSuccess: () -> Unit) {

        onSuccess()
    }
}