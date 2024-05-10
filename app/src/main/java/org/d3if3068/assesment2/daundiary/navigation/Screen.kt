package org.d3if3068.assesment2.daundiary.navigation

import org.d3if3068.assesment2.daundiary.ui.screen.KEY_ID_BUKU

sealed class Screen(val route: String) {
    data object Home : Screen("MainScren")
    data object FormInput : Screen("InputScreen")
    data object IsiScreen : Screen("IsiBuku/{$KEY_ID_BUKU}") {
        fun withId(id: Int) = "IsiBuku/$id"
    }

    data object FormUbah : Screen("inputScreen/{$KEY_ID_BUKU}") {
        fun withId(id: Int) = "inputScreen/$id"
    }
    data object Detail : Screen("DetailScreen/{$KEY_ID_BUKU}") {
        fun withId(id: Int) = "DetailScreen/$id"
    }
//    data object InputIsi : Screen("InputIsi/{$KEY_ID_BUKU}") {
//        fun withId(id: Int) = "InputIsi/$id"
//    }
}