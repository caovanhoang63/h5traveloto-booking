
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.h5traveloto_booking.auth.presentation.login.LoginScreen
import com.example.h5traveloto_booking.auth.presentation.login.LoginUIState
import com.example.h5traveloto_booking.auth.presentation.signup.SignupScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val LoginUIState = LoginUIState()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController = navController, loginUIState = LoginUIState ) }
        composable("signup") { SignupScreen(navController = navController) }
    }
}