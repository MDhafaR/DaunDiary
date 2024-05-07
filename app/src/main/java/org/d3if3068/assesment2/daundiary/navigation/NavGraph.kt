package org.d3if3068.assesment2.daundiary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3068.assesment2.daundiary.ui.screen.InputScreen
import org.d3if3068.assesment2.daundiary.ui.screen.KEY_ID_BUKU
import org.d3if3068.assesment2.daundiary.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.FormInput.route) {
            InputScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU) { type = NavType.IntType},
            )
        ){navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_BUKU)
            InputScreen(navController, id)
        }
    }
}