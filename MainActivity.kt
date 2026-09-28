package com.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui.screens.MainApp
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            enableEdgeToEdge()
        } catch (e: Exception) {
            Log.w("MainActivity", "EdgeToEdge error: ${e.message}")
        }

        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SafeMainAppWrapper()
                }
            }
        }
    }
}

@Composable
fun SafeMainAppWrapper() {
    try {
        MainApp()
    } catch (e: Exception) {
        Log.e("MainActivity", "Error loading MainApp UI: ${e.message}", e)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "App loading issue: ${e.localizedMessage ?: "Unknown Error"}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
