package org.d3if0739.assessment.navigation

sealed  class Screen (val route: String){
    data object Login: Screen("LoginScreen")
    data object Register: Screen("RegisterScreen")
    data object MainScreen: Screen("MainScreen")
    data object About: Screen("AboutScreen")
}