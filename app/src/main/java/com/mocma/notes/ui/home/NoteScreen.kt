package com.mocma.notes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mocma.notes.R
import com.mocma.notes.model.NoteEntity
import com.mocma.notes.viewmodel.HomeViewModel
import com.mocma.notes.viewmodel.NoteScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navigator: DestinationsNavigator,
    noteScreenViewModel: NoteScreenViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    id: Long = 0,
    title: String = "",
    text: String = "",
    createdAt: Long = 0L
) {
    noteScreenViewModel.id = id
    noteScreenViewModel.title = title
    noteScreenViewModel.text = text

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.go_back))
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (noteScreenViewModel.id == 0L) {
                Text(text = stringResource(R.string.add_a_note), color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(8.dp))
            }
            LazyColumn(
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                item {
                    BasicTextField(
                        value = noteScreenViewModel.title,
                        onValueChange = { noteScreenViewModel.title = it },
                        decorationBox = {
                            if (noteScreenViewModel.title.isEmpty()) Text(stringResource(R.string.title), color = Color.Gray, fontSize = MaterialTheme.typography.bodyLarge.fontSize)
                            it()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                        singleLine = true,
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(MaterialTheme.colorScheme.onBackground)
                    )

                    BasicTextField(
                        value = noteScreenViewModel.text,
                        onValueChange = { noteScreenViewModel.text = it },
                        decorationBox = {
                            if (noteScreenViewModel.text.isEmpty()) Text(stringResource(R.string.text), color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                            it()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    navigator.popBackStack()
                }) {
                    Text(text = stringResource(R.string.dismiss))
                }

                Button(onClick = {
                    homeViewModel.upsertNote(
                        NoteEntity(
                            id = noteScreenViewModel.id,
                            title = noteScreenViewModel.title,
                            text = noteScreenViewModel.text,
                            createdAt = createdAt
                        ),
                    )
                    navigator.popBackStack()
                }) {
                    Text(text = stringResource(R.string.save))
                }
            }

        }
    }
}