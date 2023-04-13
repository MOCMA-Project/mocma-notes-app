package com.mocma.notes.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mocma.notes.R

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(20.dp)
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {

        }
    }
}