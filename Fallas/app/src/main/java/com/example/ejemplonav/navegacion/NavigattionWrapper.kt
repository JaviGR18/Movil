package com.example.ejemplonav.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ejemplonav.ui.VistaHome
import com.example.ejemplonav.ui.VistaLogin
import com.example.ejemplonav.ui.VistaRegistrarFalla
import com.example.ejemplonav.ui.VistaDetalleFalla
import com.example.ejemplonav.ui.model.AlbumVIewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val viewModel: AlbumVIewModel = viewModel()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            VistaLogin {
                navController.navigate(Home)
            }
        }

        composable<Home> {
            VistaHome(
                viewModel = viewModel,
                onRegistrarFalla = { navController.navigate(RegistrarFalla()) },
                onVerDetalle = { id -> navController.navigate(DetalleFalla(fallaId = id)) }
            )
        }

        composable<RegistrarFalla> {
            VistaRegistrarFalla(
                viewModel = viewModel,
                onFallaRegistrada = { navController.popBackStack() }
            )
        }

        composable<DetalleFalla> { backStackEntry ->
            val route: DetalleFalla = backStackEntry.toRoute()
            VistaDetalleFalla(
                fallaId = route.fallaId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
