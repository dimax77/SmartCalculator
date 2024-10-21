package com.mockingb.smartcalculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mockingb.circles.CircleScreen
import com.mockingb.prime.PrimeScreen
import com.mockingb.smartcalculator.ui.MainScreen
import com.mockingb.thermometer.ThermometerScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navigateToCircles = { navController.navigate("circles") },
                navigateToPrime = { navController.navigate("prime") }) {
                navController.navigate("thermometer")
            }
        }
        composable("circles") {
            CircleScreen()
        }
        composable("prime") {
            PrimeScreen()
        }
        composable("thermometer") {
            ThermometerScreen()
        }
    }
}