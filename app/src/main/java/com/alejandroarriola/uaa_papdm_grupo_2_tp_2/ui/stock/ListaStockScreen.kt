package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.data.Producto
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
    navProductoDetalle: (Int) -> Unit = {},
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
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            var listaProductos: MutableList<Producto> = mutableListOf() //TODO: lista de prueba. Reemplazar con lista del viewmodel

            //valorss de prueba
            val prod1 = Producto(0, "Leche Lactolanda 1L", 150.0, 10, descripcion = null)
            val prod2 = Producto(1, "Manzana Colegiala", 120.0, 5, descripcion = null)
            listaProductos.add(prod1)
            listaProductos.add(prod2)
            //

            if(listaProductos.isEmpty()) {
                Text(
                    text = stringResource(R.string.Mensaje_lista_productos_vacia),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .fillMaxSize(),
                )
            } else {
                ListaProductos(
                    listaProductos = listaProductos,
                    onProductoClic = { navProductoDetalle(it.id) },
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                    contentPadding = PaddingValues(0.dp)
                )
            }
        }
    }
}

//Composables auxiliares
@Composable
fun ListaProductos(
    modifier: Modifier = Modifier,
    onProductoClic: (Producto) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    listaProductos: List<Producto> = emptyList()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = listaProductos, key = { it.id }) { item -> //debe importarse manualmente con "import androidx.compose.foundation.lazy.items"
            ProductoCard(
                producto = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onProductoClic(item) }
            )
        }
    }
}

@Composable
fun ProductoCard(
    modifier: Modifier = Modifier,
    producto: Producto
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = producto.nombre,
                style = MaterialTheme.typography.titleLarge,
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.precio_producto, producto.precio),
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.en_stock_formato, producto.cantidad),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}