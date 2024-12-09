package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino

object DetalleProductoScreen: NavDestino {
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
    navUp: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarStock(
                titulo = stringResource(DetalleProductoScreen.titulo),
                canNavUp = true,
                navUp = navUp
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.desc_retorna_home)
                )
            }
        }
    ) { innerPadding ->
        CuerpoDetalleProducto(
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
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var clicadoEliminar by rememberSaveable { mutableStateOf(false) }

        ProductoDetalleCard() //TODO: implementar

        //TODO agregar botones - y +

        OutlinedButton(
            onClick = { clicadoEliminar = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Eliminar")
        }

        if(clicadoEliminar) {
            //TODO
        }
    }
}

@Composable
fun ProductoDetalleCard(
    modifier: Modifier = Modifier
) {
    //TODO
}

@Preview(showBackground = true)
@Composable
fun DetalleProductoPreview() {
    DetalleProductoScreen(
        navUp = {}
    )
}