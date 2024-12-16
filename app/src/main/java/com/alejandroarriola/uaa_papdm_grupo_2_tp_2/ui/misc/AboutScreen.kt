package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.misc

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.TopBarStock

object AboutDestino: NavDestino {
    override val ruta = "about"
    override val titulo = R.string.about
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navReturnHome: () -> Boolean,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarStock(
                titulo = stringResource(R.string.about),
                canNavUp = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navReturnHome() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(R.string.desc_retorna_home)
                )
            }
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.Start,
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
            val appVersion = stringResource(R.string.app_version)

            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = null
            )
            Text(
                text = String.format(stringResource(R.string.about_presentacion), appVersion),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = String.format(stringResource(R.string.titulo_mapa_de_pantallas)),
                style = MaterialTheme.typography.titleMedium
            )
            Image(
                painter = painterResource(R.drawable.pantallas),
                contentDescription = stringResource(R.string.desc_mapa_pantallas),
                contentScale = ContentScale.FillWidth,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    val navController: NavHostController = rememberNavController()
    AboutScreen(navReturnHome = { navController.popBackStack() })
}
