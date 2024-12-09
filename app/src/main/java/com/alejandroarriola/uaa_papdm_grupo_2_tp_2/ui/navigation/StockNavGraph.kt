package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home.HomeDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home.HomeSreen
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.misc.AboutDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.misc.AboutScreen
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.AgregarProductoDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.AgregarProductoScreen
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.DetalleProductoScreen
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.EditarProductoDestino
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.EditarProductoScreen
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.ListaStock
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.stock.ListaStockDestino


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
            HomeSreen(
                navAboutScreen = { navController.navigate(AboutDestino.ruta) },
                navListaStock = { navController.navigate(ListaStockDestino.ruta) }
            )
        }

        composable(route = AboutDestino.ruta) {
            AboutScreen(
                navReturnHome = { navController.popBackStack() }
            )
        }

        composable(route = ListaStockDestino.ruta) {
            ListaStock(
                navAgregarProducto = { navController.navigate(AgregarProductoDestino.ruta) },
                navProductoDetalle = { navController.navigate("${DetalleProductoScreen.ruta}/${it}") },
                navUp = { navController.navigateUp() }
            )
        }

        composable(route = AgregarProductoDestino.ruta) {
            AgregarProductoScreen(
                navUp = { navController.navigateUp() }
            )
        }

        composable(
            route = DetalleProductoScreen.rutaConArgs,
            arguments = listOf(navArgument(DetalleProductoScreen.idProductoArg) {
                type = NavType.IntType
            })
        ) {
            DetalleProductoScreen(
                navUp = { navController.navigateUp() }
            )
        }

        composable(
            route = EditarProductoDestino.rutaConArgs,
            arguments = listOf(navArgument(EditarProductoDestino.idProductoArg) {
                type = NavType.IntType
            })
        ) {
            EditarProductoScreen(
                navUp = { navController.navigateUp() }
            )
        }
    }
}