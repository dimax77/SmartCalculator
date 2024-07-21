package com.mockingb.prime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PrimeScreen(viewmodel: PrimeViewModel = PrimeViewModel()) {
    var number by remember {
        mutableStateOf("")
    }

    var reverseFlag by remember {
        mutableStateOf(false)
    }

    var result by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp)
    ) {
        Text(text = "Prime numbers", style = MaterialTheme.typography.labelLarge)
        OutlinedTextField(
            value = number,
            onValueChange = { if (it.all { value -> value.isDigit() }) number = it },
            label = { Text("Enter Number") },
            singleLine = true
        )
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = reverseFlag, onCheckedChange = { checked ->
                reverseFlag = checked
            })
            Text(text = "Set lower order")
        }
        Button(onClick = { result = viewmodel.processNumber(number, reverseFlag) }) {
            Text(text = "Check number")
        }
        result?.let { result ->
            Text(
                text = result,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PrimeScreenPreview() {
    PrimeScreen()
}