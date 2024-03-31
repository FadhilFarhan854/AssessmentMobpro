package org.d3if0739.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0739.assessment.ui.screen.AboutScreen
import org.d3if0739.assessment.ui.screen.LoginScreen
import org.d3if0739.assessment.ui.screen.MainScreen
import org.d3if0739.assessment.ui.screen.MainViewModel
import org.d3if0739.assessment.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController ,
        startDestination = Screen.Login.route
        ){
        composable(route = Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(route = Screen.MainScreen.route){
            MainScreen(viewModel = MainViewModel(), navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }


    }
}