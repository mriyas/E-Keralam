package com.infoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import com.infoapp.core.network.AppNavGraph
import com.infoapp.presentation.theme.InfoAppTheme
import com.infoapp.tools.FirebaseSeeder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // ⚠️ Run ONCE to seed data, then delete these 6 lines
        FirebaseSeeder.seed(
            onSuccess = { Log.d("Seed", "✅ Done!") },
            onError   = { Log.e("Seed", "❌ ${it.message}") }
        )
        setContent {
            InfoAppTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}
