package com.jakchang.deisgnlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jakchang.deisgnlibrary.ui.screen.NavHostScreen
import com.jakchang.deisgnlibrary.ui.theme.DeisgnlibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeisgnlibraryTheme {
                NavHostScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeisgnlibraryTheme {
        Greeting("Android")
    }
}