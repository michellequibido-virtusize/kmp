package com.example.kmpdemo
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpdemo.model.User
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun UserScreen(viewModel: UserViewModel) {
    val users by viewModel.users.collectAsState()
    val platform = getPlatform()

    // Load users when screen enters composition
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Run in ${platform.name}") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            KamelImage(
                resource = asyncPainterResource(data = user.profile_image),
                contentDescription = "Network Image",
                modifier = Modifier.size(70.dp)
            )
            Text(text = user.display_name,
                style = MaterialTheme.typography.h6,
            )
//            Spacer(modifier = Modifier.width(1f))
            Button(
                onClick = {
                    openWebView(user.link)
                }
            ) {
                Text("View Profile")
            }
        }
        Divider()
    }

}