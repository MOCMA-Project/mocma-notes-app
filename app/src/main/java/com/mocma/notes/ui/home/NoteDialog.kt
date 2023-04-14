package com.mocma.notes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mocma.notes.R
import com.mocma.notes.model.NoteEntity
import com.mocma.notes.viewmodel.NoteDialogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDialog(
    noteDialogViewModel: NoteDialogViewModel,
    title: String,
    text: String,
    id: Long,
    onSave: (NoteEntity) -> Unit,
    onDismiss: () -> Unit
) {
    noteDialogViewModel.setNote(title, text, id)

    AlertDialog(onDismissRequest = onDismiss, modifier = Modifier
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.background)
        .padding(16.dp)
    ) {
        Column {
            if (id == 0L) {
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
                        value = noteDialogViewModel.title,
                        onValueChange = { noteDialogViewModel.title = it },
                        decorationBox = {
                            if (noteDialogViewModel.title.isEmpty()) Text(stringResource(R.string.title), color = Color.Gray, fontSize = MaterialTheme.typography.bodyLarge.fontSize)
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
                        value = noteDialogViewModel.text,
                        onValueChange = { noteDialogViewModel.text = it },
                        decorationBox = {
                            if (noteDialogViewModel.text.isEmpty()) Text(stringResource(R.string.text), color = Color.Gray, fontSize = MaterialTheme.typography.bodySmall.fontSize)
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
                Button(onClick = onDismiss) {
                    Text(text = stringResource(R.string.dismiss))
                }

                Button(onClick = {
                    onSave(
                        NoteEntity(
                            noteDialogViewModel.id,
                            noteDialogViewModel.title,
                            noteDialogViewModel.text
                        )
                    )
                }) {
                    Text(text = stringResource(R.string.save))
                }
            }

        }
    }
}