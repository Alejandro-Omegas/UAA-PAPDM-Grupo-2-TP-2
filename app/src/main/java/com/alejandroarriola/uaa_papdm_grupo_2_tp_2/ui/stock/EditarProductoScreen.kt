package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino

object EditarProductoDestino : NavDestino {
    override val ruta: String
        get() = "editar_producto"
    override val titulo: Int
        get() = R.string.editar_producto
    const val idProductoArg = "idProducto"
    val rutaConArgs = "$ruta/{$idProductoArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoScreen(
    modifier: Modifier = Modifier,
    navUp: () -> Unit
) {



    Scaffold(
        topBar = {
            TopBarStock(
                titulo = stringResource(EditarProductoDestino.titulo),
                canNavUp = true,
                navUp = navUp
            )
        }
    ) { innerPadding ->
        CuerpoEditarProducto(
            onProductoUpdate = {
                // Llama al método correspondiente en el ViewModel para actualizar el producto

            },
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
fun CuerpoEditarProducto(
    onProductoUpdate: () -> Unit,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            label = { Text("Producto") },
            value = "",
            onValueChange = { }
        )
        TextField(
            label = { Text("Precio") },
            value = "",
            onValueChange = { }
        )
        TextField(
            label = { Text("Cantidad") },
            value = "",
            onValueChange = { }
        )
        TextField(
            label = { Text("Detalle") },
            value = "",
            onValueChange = { }
        )

        Button(
            onClick = { onProductoUpdate() }
        ) {
            Text("Actualizar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarProductoPreview() {
    EditarProductoScreen(
        navUp = {}
    )
}
