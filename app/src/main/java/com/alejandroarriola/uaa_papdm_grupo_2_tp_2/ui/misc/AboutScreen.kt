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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R

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
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(16.dp)
        ) {
            // Título de la pantalla
            Text(
                text = "Acerca de la aplicación",
                style = MaterialTheme.typography.headlineMedium,  // Usando headlineMedium
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Descripción de la aplicación
            Text(
                text = "Esta es una aplicación de ejemplo para gestionar productos. Permite agregar, editar y eliminar productos de un inventario.",
                style = MaterialTheme.typography.bodyLarge,  // Usando bodyLarge
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Información del desarrollador
            Text(
                text = "Desarrollado por: Alumnos de la UAA",
                style = MaterialTheme.typography.bodyMedium,  // Usando bodyMedium
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Información de contacto para soporte
            Text(
                text = "Soporte: soporte@miapp.com",
                style = MaterialTheme.typography.bodyMedium,  // Usando bodyMedium
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Versión de la aplicación
            Text(
                text = "Versión 1.0",
                style = MaterialTheme.typography.bodySmall,  // Usando bodySmall
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AboutScreenPreview() {
    val navController: NavHostController = rememberNavController()
    AboutScreen(navReturnHome = { navController.popBackStack() })
}
