package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.misc

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino

object AboutDestino: NavDestino {
    override val ruta = "about"
    override val titulo = R.string.about
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navReturnHome: () -> Boolean,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
        }
    ) {
        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(text = "About Screen")
            Text(text = "Version 1.0")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    val navController: NavHostController = rememberNavController()
    AboutScreen(navReturnHome = { navController.popBackStack() })
}