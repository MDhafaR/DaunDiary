package org.d3if3068.assesment2.daundiary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3068.assesment2.daundiary.database.BukuDb
import org.d3if3068.assesment2.daundiary.ui.screen.DetailScreen
import org.d3if3068.assesment2.daundiary.ui.screen.InputScreen
import org.d3if3068.assesment2.daundiary.ui.screen.IsiBuku
import org.d3if3068.assesment2.daundiary.ui.screen.KEY_ID_BUKU
import org.d3if3068.assesment2.daundiary.ui.screen.MainScreen
import org.d3if3068.assesment2.daundiary.util.ViewModelFactory

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // ini adalah navigation biasa ke home
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        // ini adalah navigation ke page Detail dengan menggunakan id dari mainScreen
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU) { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_BUKU)
            DetailScreen(navController, id)
        }

        // ini adalah navigation ke InputScreen (ubah cover) yang dimana masih membutuhkan id dari DetailScreen
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU) { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_BUKU)
            InputScreen(onNavigateToScreen = { judul, deskripsi, pengarang, warna ->
            }, navController = navController, id)
        }

        // ini adalah navigation ke InputScreen tanpa menggunakan id (input data baru)
        composable(
            route = Screen.FormInput.route
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_BUKU)
            InputScreen(onNavigateToScreen = { judul, deskripsi, pengarang, warna ->
                navController.navigate("isiScreen/$judul/$deskripsi/$pengarang/$warna")
            }, navController)
        }

        // ini adalah navigasi ke IsiBuku dari InputScreen dengan mengirimkan paramater
        composable(
            route = "isiScreen/{judul}/{deskripsi}/{pengarang}/{warna}",
            arguments = listOf(
                navArgument("judul") {
                    type = NavType.StringType
                },
                navArgument("deskripsi") {
                    type = NavType.StringType
                },
                navArgument("pengarang") {
                    type = NavType.StringType
                },
                navArgument("warna") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val judul = backStackEntry.arguments?.getString("judul") ?: ""
            val deskripsi = backStackEntry.arguments?.getString("deskripsi") ?: ""
            val pengarang = backStackEntry.arguments?.getString("pengarang") ?: ""
            val warna = backStackEntry.arguments?.getInt("warna") ?: 0
            IsiBuku(
                judul = judul,
                deskripsi = deskripsi,
                pengarang = pengarang,
                warna = warna,
                navController = navController
            )
        }

        composable(
            route = Screen.IsiScreen.route,
            arguments = listOf(
                navArgument(KEY_ID_BUKU) { type = NavType.IntType },
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(KEY_ID_BUKU)
            IsiBuku(
                judul = "",
                deskripsi = "",
                pengarang = "",
                warna = 0,
                navController = navController,
                id
            )
        }
    }
}