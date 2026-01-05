# Kotlin Multiplatform (KMP) User App

This project is a **Kotlin Multiplatform Mobile (KMM/KMP) application** that demonstrates a shared user interface and network logic between Android and iOS. The app fetches a list of StackOverflow users via the StackExchange API and supports web content display through a WebView.

## Features

- **Shared User Interface**: Built entirely with Compose for both platforms.  
- **User List Screen**: Displays a list of users retrieved from the StackExchange API.  
- **WebView Handler**: Opens user-related web content when needed.  
- **Networking**: Implemented with [Ktor](https://ktor.io/) client.  
- **Serialization**: JSON conversion using Kotlinx Serialization.  
- **Image Loading**: Network images loaded via [Kamel](https://github.com/alialbaali/Kamel).  
- **ViewModel**: Shared `UserViewModel` to manage UI state and networking.  

## Project Structure
<img width="657" height="337" alt="Screenshot 2026-01-05 at 11 38 24 AM" src="https://github.com/user-attachments/assets/5c0c8a19-5e38-48c8-9b37-43b45f8532f1" />

### Key Shared Components

- **APIService and NetworkClient**: Handles API requests via Ktor.
  ```kotlin
   client.get("https://api.stackexchange.com/2.3/users?site=stackoverflow&page=1&pagesize=20").body()
- **UserScreen**: Compose-based screen displaying the list of users with network-loaded images.

- **WebViewHandler**: Cross-platform WebView implementation for showing user pages.

- **UserViewModel**: Maintains UI state, handles API requests, and exposes data to UserScreen.

### Dependencies

- **Ktor** – for network requests

- **Kotlinx Serialization** – for JSON parsing

- **Kamel** – for loading images from the network

- **Jetpack Compose / Compose Multiplatform** – for building UI

- **Kotlinx Coroutines** – for asynchronous operations

### Getting Started

- Clone the repository:

   git clone https://github.com/michellequibido-virtusize/kmp.git
  
- Open the project in Android Studio / IntelliJ IDEA with KMM support.
  
- Sync Gradle to fetch dependencies.
  
- Run this command to create the xcframework for iOS

  **./gradlew :shared:assembleXCFramework**
  
- Add the shared.xcframework to iOSApp -> Frameworks

- Run the Android or iOS app target. The shared UserScreen and WebViewHandler will work across both platforms.
