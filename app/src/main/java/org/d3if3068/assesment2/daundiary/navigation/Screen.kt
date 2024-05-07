package org.d3if3068.assesment2.daundiary.navigation

import org.d3if3068.assesment2.daundiary.ui.screen.KEY_ID_BUKU

sealed class Screen(val route: String) {
    data object Home : Screen("MainScren")
    data object FormInput : Screen("InputScreen")

    data object FormUbah : Screen("inputScreen/{$KEY_ID_BUKU}") {
        fun withId(id: Int) = "inputScreen/$id"
    }
}