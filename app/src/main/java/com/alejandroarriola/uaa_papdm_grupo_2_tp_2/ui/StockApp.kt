package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.StockNavGraph

@Composable
fun StockApp(navController: NavHostController = rememberNavController()) {
   StockNavGraph(navController = navController)
}