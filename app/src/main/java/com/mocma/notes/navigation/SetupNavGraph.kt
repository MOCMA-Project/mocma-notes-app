package com.mocma.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mocma.notes.ui.home.HomeScreen
import com.mocma.notes.ui.home.NoteDialog
import com.mocma.notes.viewmodel.HomeViewModel
import com.mocma.notes.viewmodel.NoteDialogViewModel

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    noteDialogViewModel: NoteDialogViewModel
) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(homeViewModel = homeViewModel, navController = navHostController)
        }
        composable(Screen.Note.route) {
            NoteDialog(
                navController = navHostController,
                noteDialogViewModel = noteDialogViewModel,
                onSave = { note ->
                    homeViewModel.upsertNote(note)
                    navHostController.popBackStack()
                },
                onDismiss = { navHostController.popBackStack() }
            )
        }
    }
}