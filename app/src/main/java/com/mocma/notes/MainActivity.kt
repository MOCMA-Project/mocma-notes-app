package com.mocma.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mocma.notes.ui.home.HomeScreen
import com.mocma.notes.ui.theme.MOCMANotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCMANotesTheme {
                HomeScreen()
            }
        }
    }
}