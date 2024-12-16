import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class EditarProductoViewModel : ViewModel() {


    var producto: String = "" // Nombre del producto
    var precio: String = ""   // Precio del producto
    var cantidad: String = "" // Cantidad del producto
    var detalle: String = ""  // Detalle del producto


    fun updateProducto() {




        viewModelScope.launch {

            println("Actualizando producto: $producto")
            println("Precio: $precio")
            println("Cantidad: $cantidad")
            println("Detalle: $detalle")


        }
    }
}