package com.mocma.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mocma.notes.navigation.SetupNavGraph
import com.mocma.notes.ui.theme.MOCMANotesTheme
import com.mocma.notes.viewmodel.HomeViewModel
import com.mocma.notes.viewmodel.NoteDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()
    private val noteDialogViewModel by viewModels<NoteDialogViewModel>()

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCMANotesTheme {
                navController = rememberNavController()

                SetupNavGraph(
                    navHostController = navController,
                    homeViewModel = homeViewModel,
                    noteDialogViewModel = noteDialogViewModel
                )
            }
        }
    }
}