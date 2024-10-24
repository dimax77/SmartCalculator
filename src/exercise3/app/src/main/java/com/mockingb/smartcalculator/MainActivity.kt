package com.mockingb.smartcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mockingb.logger.AppLogger
import com.mockingb.smartcalculator.ui.theme.SmartCalculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppLogger.log(AppLogger.LogLevel.DEBUG, "Main Activity", "Created")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}

@Composable
fun App(modifier: Modifier = Modifier) {
    AppLogger.log(AppLogger.LogLevel.DEBUG, "App", "Started")
    val navController: NavHostController = rememberNavController()
    NavGraph(navController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartCalculatorTheme {
        App()
    }
}