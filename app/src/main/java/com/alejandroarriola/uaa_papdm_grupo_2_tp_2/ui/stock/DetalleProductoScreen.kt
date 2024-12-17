package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.Producto
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.AppViewModelProvider
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino
import kotlinx.coroutines.launch

object DetalleProductoDestino: NavDestino {
    override val ruta: String
        get() = "detalle_producto"
    override val titulo: Int
        get() = R.string.detalle_producto
    const val idProductoArg = "idProducto"
    val rutaConArgs = "$ruta/{$idProductoArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleProductoScreen(
    modifier: Modifier = Modifier,
    navEditarProducto: (Int) -> Unit = {},
    navUp: () -> Unit,
    viewModel: DetalleProductoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBarStock(
                titulo = stringResource(DetalleProductoDestino.titulo),
                canNavUp = true,
                navUp = navUp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navEditarProducto(uiState.value.productoDetalles.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.desc_btnEditarProducto)
                )
            }
        }
    ) { innerPadding ->
        CuerpoDetalleProducto(
            onEliminarProducto = {
                coroutineScope.launch {
                    viewModel.eliminarProducto()
                    navUp()
                }
            },
            onAumentarUno = viewModel::aumentarUno,
            onReducirUno = { viewModel.reducirUno() },
            uiState = uiState.value,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun CuerpoDetalleProducto(
    modifier: Modifier = Modifier,
    onEliminarProducto: () -> Unit = {},
    onAumentarUno: () -> Unit = {},
    onReducirUno: () -> Unit = {},
    uiState: DetalleProductoUiState
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var clicadoEliminar by rememberSaveable { mutableStateOf(false) }

        //Card del producto
        ProductoDetalleCard(
            modifier = Modifier.fillMaxWidth(),
            producto = uiState.productoDetalles.toProducto()
        )

        //Botones - y +
        Row(
            horizontalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.weight(2f))
            OutlinedButton(
                onClick = onReducirUno,
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = "-",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick =
                { if(uiState.productoDetalles.cantidad.toIntOrNull() != null
                    && uiState.productoDetalles.cantidad.toInt() < 99999999) {
                    onAumentarUno()
                }},
                shape = MaterialTheme.shapes.small,
            ) {
                Text(
                    text = "+",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
            Spacer(modifier = Modifier.weight(2f))
        }

        //Boton eliminar
        OutlinedButton(
            onClick = { clicadoEliminar = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.txt_eliminar_bttn))
        }

        if(clicadoEliminar) {
            AlertDialog(
                onDismissRequest = { clicadoEliminar = false },
                title = { Text(stringResource(R.string.mensaje_de_confirmacion)) },
                text = { Text(stringResource(R.string.pregunta_eliminar_producto)) },
                modifier = modifier,
                dismissButton = {
                    OutlinedButton(onClick = { clicadoEliminar = false }) {
                        Text(stringResource(R.string.no))
                    }
                },
                confirmButton = {
                    TextButton(onClick = { onEliminarProducto() }) {
                        Text(stringResource(R.string.si))
                    }
                }
            )
        }
    }
}

@Composable
fun ProductoDetalleCard(
    modifier: Modifier = Modifier,
    producto: Producto
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            if(producto.nombre.length <= 25) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(stringResource(R.string.lblProducto))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = producto.nombre, fontWeight = FontWeight.Bold)
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(stringResource(R.string.lblProducto))
                    Text(text = producto.nombre, fontWeight = FontWeight.Bold)
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(stringResource(R.string.lblCantidad))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = producto.cantidad.toString(), fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(stringResource(R.string.lblPrecio))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = producto.formatedPrice(), fontWeight = FontWeight.Bold)
            }
            if(producto.detalle != null && producto.detalle != "") {
                Column(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(stringResource(R.string.lblDetalle))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = producto.detalle, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
