package org.d3if3068.assesment2.daundiary.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("MainScren")
    data object FormInput : Screen("InputScreen")
}