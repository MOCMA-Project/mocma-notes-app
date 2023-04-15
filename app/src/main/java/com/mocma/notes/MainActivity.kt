package com.mocma.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mocma.notes.ui.home.NavGraphs
import com.mocma.notes.ui.theme.MOCMANotesTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCMANotesTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}