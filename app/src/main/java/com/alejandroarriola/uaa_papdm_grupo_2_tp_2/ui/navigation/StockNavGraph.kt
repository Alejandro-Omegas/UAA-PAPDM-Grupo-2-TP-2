package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home.HomeDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home.HomeSreen

@Composable
fun StockNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestino.ruta
    ) {
        composable(route = HomeDestino.ruta) {
            HomeSreen()
        }
    }
}