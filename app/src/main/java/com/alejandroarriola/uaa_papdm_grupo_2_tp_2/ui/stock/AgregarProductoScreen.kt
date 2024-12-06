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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino


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
    navUp: () -> Unit = {},
    //TODO: codear y agregar viewmodel
) {
    Scaffold(
        topBar = {
            TopBarStock(
                titulo = stringResource(AgregarProductoDestino.titulo),
                canNavUp = true,
                navUp = navUp,
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
            Row {
                Text("campo")
                TextField(label = {
                    Text("atributo") },
                    value = "",
                    onValueChange = { }
                )
            }
            Button(
                onClick = { /*TODO:invocar Agregar del VM*/}
            ) {
                Text("Agregar")
            }
        }
    }
}