package com.app.superapp.core.navigation

sealed class Routes(val route: String) {
    // Auth
    object Splash : Routes("splash")
    object Onboarding : Routes("onboarding")
    object LanguageSelect : Routes("language_select")
    object Login : Routes("login")
    object OtpVerification : Routes("otp_verification/{mobile}") {
        fun createRoute(mobile: String) = "otp_verification/$mobile"
    }
    object PinSetup : Routes("pin_setup")
    object Register : Routes("register")
    object KycUpload : Routes("kyc_upload")
    object AuthSuccess : Routes("auth_success")
    
    // Main
    object Main : Routes("main")
    object Home : Routes("home")
    object Banking : Routes("banking")
    object Payments : Routes("payments")
    object Services : Routes("services")
    object Profile : Routes("profile")
    
    // Payments
    object QrScan : Routes("qr_scan")
    object PaymentEntry : Routes("payment_entry/{payeeName}/{upiId}") {
        fun createRoute(name: String, id: String) = "payment_entry/$name/$id"
    }
    object PaymentSuccess : Routes("payment_success/{amount}/{payeeName}") {
        fun createRoute(amount: String, name: String) = "payment_success/$amount/$name"
    }
    
    // Wallet
    object Wallet : Routes("wallet")
    
    // UPI
    object UpiDashboard : Routes("upi_dashboard")
    object SendMoneyUpi : Routes("send_money_upi")
    
    // Bill Payments
    object MobileRecharge : Routes("mobile_recharge")
    object DTH : Routes("dth")
    object Electricity : Routes("electricity")
    
    // Travel
    object BusBooking : Routes("bus_booking")
    object FlightBooking : Routes("flight_booking")
    
    // Govt Services
    object NewPAN : Routes("new_pan")
    object GSTRegistration : Routes("gst_registration")
}
