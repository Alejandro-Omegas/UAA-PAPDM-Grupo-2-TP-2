package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino


object ListaStockDestino: NavDestino {
    override val ruta: String
        get() = "lista_stock"
    override val titulo: Int
        get() = R.string.lista_de_stock
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListaStock(
    modifier: Modifier = Modifier,
    navAgregarProducto: () -> Unit = {},
    navUp: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBarStock(
                titulo = stringResource(ListaStockDestino.titulo),
                canNavUp = true,
                navUp = navUp,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navAgregarProducto() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.desc_agregar_producto)
                )
            }
        }
    ) {
        Text(text = stringResource(R.string.lista_de_stock))
    }
}
