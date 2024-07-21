package com.mockingb.circles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mockingb.logger.AppLogger


@Composable
fun CircleScreen(circleViewModel: CircleViewModel = CircleViewModel()) {
    var x1 by remember { mutableStateOf("") }
    var y1 by remember { mutableStateOf("") }
    var r1 by remember { mutableStateOf("") }
    var x2 by remember { mutableStateOf("") }
    var y2 by remember { mutableStateOf("") }
    var r2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.systemBars),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Circle 1", style = MaterialTheme.typography.titleLarge)
        CircleInputField("x1", x1) { x1 = it }
        CircleInputField("y1", y1) { y1 = it }
        CircleInputField("r1", r1) { r1 = it }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Circle 2", style = MaterialTheme.typography.titleLarge)
        CircleInputField("x2", x2) { x2 = it }
        CircleInputField("y2", y2) { y2 = it }
        CircleInputField("r2", r2) { r2 = it }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            AppLogger.log("\"Check circles\" Button pressed")
            result = circleViewModel.checkCircles(
                x1.toDoubleOrNull(),
                y1.toDoubleOrNull(),
                r1.toDoubleOrNull(),
                x2.toDoubleOrNull(),
                y2.toDoubleOrNull(),
                r2.toDoubleOrNull()
            )
        }) {
            Text("Check circles")
        }
        result?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
//        AppLogger.log("$this: $result", AppLogger.LogLevel.INFO)

    }

}

@Composable
fun CircleInputField(label: String, value: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun CircleScreenPreview() {
    val dummyViewModel = DummyCircleViewModel()
    CircleScreen(dummyViewModel)
}

class DummyCircleViewModel : CircleViewModel() {
    override fun checkCircles(
        x1: Double?,
        y1: Double?,
        r1: Double?,
        x2: Double?,
        y2: Double?,
        r2: Double?
    ): String {
        return "This is a dummy viewmodel"
    }
}