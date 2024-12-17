package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.MainActivity
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.TopBarStock
import kotlin.system.exitProcess

object HomeDestino: NavDestino {
    override val ruta = "home"
    override val titulo = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeSreen(
    modifier: Modifier = Modifier,
    navAboutScreen: () -> Unit = {},
    navListaStock: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarStock(
                titulo = stringResource(R.string.app_name),
                canNavUp = false
            )
        },
        bottomBar = {
            BottomAppBar {
                OutlinedButton(
                    onClick = { navAboutScreen() }
                ) {
                    Text(text = stringResource(R.string.about))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { exitApp() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.exits_the_app)
                )
            }
        }
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = { navListaStock() },
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_large))
                    .size(width = 200.dp, height = 80.dp)
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .shadow(
                        elevation = 10.dp,
                        shape = MaterialTheme.shapes.extraLarge,
                        clip = false)
            ) {
                Text(
                    text = stringResource(R.string.ver_stock),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            }
        }
    }
}

fun exitApp() {
    val activity = MainActivity()
    activity.finish()
    exitProcess(0)
}

@Preview(showBackground = true)
@Composable
fun HomeSreenPreview() {
    HomeSreen()
}
