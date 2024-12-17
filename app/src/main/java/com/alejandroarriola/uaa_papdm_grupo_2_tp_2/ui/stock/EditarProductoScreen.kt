package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.AppViewModelProvider
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.misc.LimiterRemoval
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale

object EditarProductoDestino: NavDestino {
    override val ruta: String
        get() = "editar_producto"
    override val titulo: Int
        get() = R.string.editar_producto
    const val idProductoArg = "idProducto"
    val rutaConArgs = "$ruta/{$idProductoArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoScreen(
    modifier: Modifier = Modifier,
    navUp: () -> Unit,
    navBack: () -> Unit,
    viewModel: EditarProductoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBarStock(
                titulo = stringResource(EditarProductoDestino.titulo),
                canNavUp = true,
                navUp = navUp
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .padding(
                        start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                        end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                        top = innerPadding.calculateTopPadding()
                    )
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = viewModel.productoUiState.productoDetalles.nombre,
                    onValueChange = {
                        if (it.length <= LimiterRemoval.longTextoCorto) {
                            viewModel.actualizarUiState(
                                viewModel.productoUiState.productoDetalles.copy(nombre = it)
                            )
                        }
                    },
                    label = { Text(stringResource(R.string.nombre_producto_requerido)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = viewModel.productoUiState.productoDetalles.precio,
                    onValueChange = {
                        if (it.toDoubleOrNull() != null && it.toDouble() <= LimiterRemoval.limPrecio
                            && it.length <= LimiterRemoval.longPrecio) {
                            viewModel.actualizarUiState(
                                viewModel.productoUiState.productoDetalles.copy(precio = it)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    label = { Text(stringResource(R.string.precio_requerido)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = viewModel.productoUiState.productoDetalles.cantidad,
                    onValueChange = {
                        if (it.toIntOrNull() != null && it.toInt() <= LimiterRemoval.longCantidad) {
                            viewModel.actualizarUiState(
                                viewModel.productoUiState.productoDetalles.copy(cantidad = it)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(stringResource(R.string.cantidad_requerida)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    singleLine = true
                )
                OutlinedTextField(
                    value = viewModel.productoUiState.productoDetalles.detalle,
                    onValueChange = {
                        if (it.length <= LimiterRemoval.LongTextoLargo) {
                            viewModel.actualizarUiState(
                                viewModel.productoUiState.productoDetalles.copy(detalle = it)
                            )
                        }
                    },
                    label = { Text(stringResource(R.string.detalle_no_requerido)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    singleLine = false
                )

                Text(
                    text = stringResource(R.string.campos_requeridos),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.actualizarProducto()
                            navBack()
                        }
                    },
                    enabled = viewModel.productoUiState.isEntryValid,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(R.string.lblBotonActualizar))
                }
            }
        }
    }
}
