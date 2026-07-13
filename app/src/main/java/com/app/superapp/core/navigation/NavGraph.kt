package com.app.superapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import com.app.superapp.ui.auth.*
import com.app.superapp.ui.banking.*
import com.app.superapp.ui.dashboard.*
import com.app.superapp.ui.payments.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(onNavigate = { route ->
                navController.navigate(route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            })
        }
        
        composable(Routes.Onboarding.route) {
            OnboardingScreen(onNavigate = { route ->
                navController.navigate(route)
            })
        }
        
        composable(Routes.LanguageSelect.route) {
            LanguageSelectScreen(onNavigate = { route ->
                navController.navigate(route)
            })
        }
        
        composable(Routes.Login.route) {
            LoginScreen(onNavigate = { route ->
                navController.navigate(route)
            })
        }
        
        composable(Routes.OtpVerification.route) { backStackEntry ->
            val mobile = backStackEntry.arguments?.getString("mobile") ?: ""
            OtpVerificationScreen(mobileNumber = mobile, onNavigate = {
                navController.navigate(Routes.PinSetup.route)
            })
        }
        
        composable(Routes.PinSetup.route) {
            PinSetupScreen(onNavigate = {
                navController.navigate(Routes.AuthSuccess.route)
            })
        }
        
        composable(Routes.Register.route) {
            RegistrationScreen(onNavigate = {
                navController.navigate(Routes.KycUpload.route)
            })
        }
        
        composable(Routes.KycUpload.route) {
            KycUploadScreen(onNavigate = {
                navController.navigate(Routes.AuthSuccess.route)
            })
        }
        
        composable(Routes.AuthSuccess.route) {
            AuthSuccessScreen(onNavigate = {
                navController.navigate(Routes.Main.route) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
        
        composable(Routes.Main.route) {
            MainScreen(
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToService = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Routes.Banking.route) {
            BankingDashboardScreen(
                onServiceClick = { route -> navController.navigate(route) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.AEPS.route) {
            AepsFlow(onBack = { navController.popBackStack() })
        }

        composable(Routes.DMT.route) {
            DmtFlow(onBack = { navController.popBackStack() })
        }

        composable(Routes.MiniATM.route) {
            MiniAtmFlow(onBack = { navController.popBackStack() })
        }
        
        composable("cash_deposit") {
            CashDepositFlow(onBack = { navController.popBackStack() })
        }
        
        composable("balance_enquiry") {
            BalanceEnquiryFlow(onBack = { navController.popBackStack() })
        }
        
        composable("mini_statement") {
            TransactionHistoryScreen(onBack = { navController.popBackStack() })
        }

        // --- Payments (Phase 18-22) ---
        composable(Routes.QrScan.route) {
            QrScannerScreen(
                onScanResult = { name, id ->
                    navController.navigate(Routes.PaymentEntry.createRoute(name, id))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PaymentEntry.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("payeeName") ?: ""
            val id = backStackEntry.arguments?.getString("upiId") ?: ""
            PaymentEntryScreen(
                payeeName = name,
                upiId = id,
                onPaymentComplete = { amount ->
                    navController.navigate(Routes.PaymentSuccess.createRoute(amount, name))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PaymentSuccess.route) { backStackEntry ->
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            val name = backStackEntry.arguments?.getString("payeeName") ?: ""
            PaymentSuccessScreen(
                amount = amount,
                payeeName = name,
                onDone = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.Main.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Wallet.route) {
            WalletScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.UpiDashboard.route) {
            UpiDashboardScreen(
                onBack = { navController.popBackStack() },
                onAction = { action ->
                    when (action) {
                        "scan" -> navController.navigate(Routes.QrScan.route)
                        "send" -> navController.navigate(Routes.SendMoneyUpi.route)
                    }
                }
            )
        }
        
        composable(Routes.Payments.route) {
            UpiDashboardScreen(onBack = {}, onAction = { action ->
                if (action == "scan") navController.navigate(Routes.QrScan.route)
            })
        }

        // --- Bill Payments (Phase 23-29) ---
        composable("bill_pay/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            BillPayFlow(category = category, onBack = { navController.popBackStack() })
        }

        // --- Travel (Phase 30-35) ---
        composable("travel_dashboard") {
            TravelDashboardScreen(
                onCategoryClick = { category ->
                    if (category == "Bus") navController.navigate("bus_booking")
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("bus_booking") {
            BusBookingFlow(onBack = { navController.popBackStack() })
        }

        // --- Insurance (Phase 36-40) ---
        composable("insurance_dashboard") {
            InsuranceDashboardScreen(
                onCategoryClick = { type -> navController.navigate("insurance_flow/\$type") },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("insurance_flow/{type}") { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            InsuranceFlow(type = type, onBack = { navController.popBackStack() })
        }

        // --- Govt Services (Phase 41-52) ---
        composable("gov_dashboard") {
            GovServicesDashboard(
                onServiceClick = { service ->
                    if (service == "pan") navController.navigate("pan_flow")
                },
                onBack = { navController.popBackStack() }
            )
        }
        
        composable("pan_flow") {
            PanFlow(onBack = { navController.popBackStack() })
        }

        // --- Support & Jobs (Phase 53-58) ---
        composable("support_dashboard") {
            SupportDashboard(onBack = { navController.popBackStack() })
        }
        
        composable("job_listing") {
            JobListingScreen(onBack = { navController.popBackStack() })
        }

        // --- Profile & Analytics (Phase 59-62) ---
        composable("edit_profile") { EditProfileScreen(onBack = { navController.popBackStack() }) }
        composable("my_documents") { DocumentsScreen(onBack = { navController.popBackStack() }) }
        composable("analytics") { AnalyticsScreen(onBack = { navController.popBackStack() }) }
        composable("settings") { SettingsScreen(onBack = { navController.popBackStack() }) }
        composable("notifications") { NotificationScreen(onBack = { navController.popBackStack() }) }
    }
}
