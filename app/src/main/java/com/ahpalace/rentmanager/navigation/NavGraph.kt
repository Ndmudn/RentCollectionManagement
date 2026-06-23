package com.ahpalace.rentmanager.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahpalace.rentmanager.ui.auth.LoginScreen
import com.ahpalace.rentmanager.ui.auth.OtpScreen
import com.ahpalace.rentmanager.ui.main.DashboardScreen

object Routes {
    const val LOGIN = "auth/login"
    const val OTP = "auth/otp"
    const val DASHBOARD = "main/dashboard"
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.OTP) { OtpScreen(navController) }
        composable(Routes.DASHBOARD) { DashboardScreen(navController) }
    }
}
