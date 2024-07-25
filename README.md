Album App
Album App is a simple Android application that fetches data from a public API and displays a list of albums using RecyclerView. It follows the MVVM architecture pattern for structuring the app components.

Features :
Fetches album data from the JSONPlaceholder API.
Displays the list of albums in a RecyclerView.
Handles network failures and API errors with informative error messages.
Uses LiveData and ViewModel from Android Architecture Components for managing UI-related data in a lifecycle-conscious way.

Dependencies :
ViewModel and LiveData: Used from Android Jetpack's Lifecycle library (androidx.lifecycle).
RecyclerView: Displays the list of albums (androidx.recyclerview).
Retrofit: For making network requests and retrieving data (com.squareup.retrofit2).
Gson: JSON parsing library for Retrofit (com.google.code.gson).

How to run :
Clone the repository and open the project in Android Studio. Connect your Android device or use an emulator. Build and run the app.

Configuration :
Ensure you have an active internet connection to fetch data from the API.

Error Handling :
Network failures and API errors are handled gracefully.
If the initial data loading fails due to internet connectivity issues, a "Refresh" button is available in the UI to reload the data
Error messages are displayed via Toast messages in the UI (MainActivity.kt).
