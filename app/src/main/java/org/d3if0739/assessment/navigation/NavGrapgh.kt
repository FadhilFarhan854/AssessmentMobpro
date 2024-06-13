package org.d3if0739.assessment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0739.assessment.ui.screen.AboutScreen
import org.d3if0739.assessment.ui.screen.DetailScreen
import org.d3if0739.assessment.ui.screen.KEY_ID_CATATAN
import org.d3if0739.assessment.ui.screen.LoginScreen
import org.d3if0739.assessment.ui.screen.MainScreen
import org.d3if0739.assessment.ui.screen.MainViewModel
import org.d3if0739.assessment.ui.screen.OnlineModeScreen
import org.d3if0739.assessment.ui.screen.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController ,
        startDestination = Screen.OnlineMode.route
        ){
        composable(route = Screen.Login.route){
            LoginScreen(navController = navController)
        }
        composable(route = Screen.OnlineMode.route){
            OnlineModeScreen(navController)
        }
        composable(route = Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(route = Screen.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.FormBaru.route){
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CATATAN){
                    type = NavType.LongType
                }
            )
        ){
                navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailScreen(navController, id)
        }


    }
}