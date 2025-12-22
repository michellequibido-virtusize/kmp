import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmpdemo.UserScreen
import com.example.kmpdemo.UserViewModel
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    ComposeUIViewController {
        UserScreen(UserViewModel())
    }