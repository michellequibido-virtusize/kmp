package com.example.kmpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kmpdemo.network.APIService
import com.example.kmpdemo.network.provideHttpClient

class MainActivity : ComponentActivity() {
    private val viewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UserScreen(viewModel)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}