package org.d3if0739.assessment.navigation

import org.d3if0739.assessment.ui.screen.KEY_ID_CATATAN

sealed  class Screen (val route: String){
    data object Login: Screen("LoginScreen")
    data object OnlineMode: Screen("OnlineModeScreen")
    data object Register: Screen("RegisterScreen")
    data object MainScreen: Screen("MainScreen")
    data object About: Screen("AboutScreen")
    data object FormBaru: Screen("detailScreen")

    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}