//EVENTO AGREGAR PRODUCTO
package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.AppViewModelProvider
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino
import kotlinx.coroutines.CoroutineScope


object AgregarProductoDestino: NavDestino {
    override val ruta: String
        get() = "agregar_producto"
    override val titulo: Int
        get() = R.string.agregar_producto
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AgregarProductoScreen(
    modifier: Modifier = Modifier,
    viewModel: AgregarProductoViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navUp: () -> Unit = {},
    navBack: () -> Unit = {}
) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(AgregarProductoDestino.titulo)) }, // el titulo dinámico
                navigationIcon = {
                    IconButton(onClick = navUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
           // para el nombre
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // espacio entre los elementos
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nombre:", modifier = Modifier.weight(1f)) // tamaño del texto
                TextField(
                    value = viewModel.productoUiState.productoDetalles.nombre,
                    onValueChange = { viewModel.actualizarUiState(
                        viewModel.productoUiState.productoDetalles.copy(nombre = it)) },
                    label = { Text("Ingrese el nombre del producto") },
                    modifier = Modifier.weight(2f) // controla el tamaño del campo de texto
                )
            }

            // para el precio
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Precio:", modifier = Modifier.weight(1f))
                TextField(
                    value = viewModel.productoUiState.productoDetalles.precio,
                    onValueChange = { viewModel.actualizarUiState(
                        viewModel.productoUiState.productoDetalles.copy(precio = it)) },
                    label = { Text("Ingrese el precio del producto") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(2f)
                )
            }

            // para la cantidad
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cantidad:", modifier = Modifier.weight(1f))
                TextField(
                    value = viewModel.productoUiState.productoDetalles.cantidad,
                    onValueChange = { viewModel.actualizarUiState(
                        viewModel.productoUiState.productoDetalles.copy(cantidad = it)) },
                    label = { Text("Ingrese la cantidad en stock") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(2f)
                )
            }

            // para la descripcion
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Descripción:", modifier = Modifier.weight(1f))
                TextField(
                    value = viewModel.productoUiState.productoDetalles.detalle,
                    onValueChange = { viewModel.actualizarUiState(
                        viewModel.productoUiState.productoDetalles.copy(detalle = it)) },
                    label = { Text("Ingrese una descripción (opcional)") },
                    modifier = Modifier.weight(2f)
                )
            }

            // boton para agregar el producto
            Button(
                onClick = {
                    viewModel.agregarProducto()
                    navBack()
                          }, // lo del viewmodel
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Agregar")
            }
        }
    }
}


