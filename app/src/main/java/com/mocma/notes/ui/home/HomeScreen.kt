package com.mocma.notes.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mocma.notes.R
import com.mocma.notes.ui.home.destinations.NoteScreenDestination
import com.mocma.notes.viewmodel.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val notes = homeViewModel.notes.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            IconButton(onClick = {
                navigator.navigate(
                    NoteScreenDestination(title = "", text = "")
                )
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_a_note)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (notes.value.isEmpty()) {
                item {
                    Text(text = stringResource(R.string.no_note_yet), fontSize = MaterialTheme.typography.bodyLarge.fontSize, fontWeight = FontWeight.Bold)
                }
            } else {
                items(notes.value) { note ->
                    Column(
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {
                                    navigator.navigate(
                                        NoteScreenDestination(
                                            note.id,
                                            note.title,
                                            note.text,
                                            note.createdAt
                                        )
                                    )
                                },
                                onLongClick = {
                                    homeViewModel.deleteNote(note)
                                }
                            )
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.onBackground,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(text = note.title, fontSize = MaterialTheme.typography.bodyLarge.fontSize, fontWeight = FontWeight.Bold)
                        Text(text = note.text, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
