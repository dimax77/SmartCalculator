package com.mockingb.thermometer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


fun isValidDouble(number: String): Boolean {
    return number.toDoubleOrNull() != null
}

@Composable
fun ThermometerScreen(viewModel: ThermometerViewModel = ThermometerViewModel()) {
    var number by remember {
        mutableStateOf("")
    }
    var season by remember {
        mutableStateOf(ThermometerViewModel.Season.SUMMER)
    }

    var scaleType by remember {
        mutableStateOf(ThermometerViewModel.ScaleType.CELSIUS)
    }

    var result by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(text = "Thermometer", style = MaterialTheme.typography.labelLarge)
        OutlinedTextField(
            value = number,
            onValueChange = { if (isValidDouble(it)) number = it },
            label = { Text("Enter temperature") },
            singleLine = true
        )
        Text(text = "Select season", style = MaterialTheme.typography.labelLarge)
        Row {
            RadioButton(
                selected = season == ThermometerViewModel.Season.SUMMER,
                onClick = { season = ThermometerViewModel.Season.SUMMER })
            Text(text = "Summer")
            RadioButton(
                selected = season == ThermometerViewModel.Season.WINTER,
                onClick = { season = ThermometerViewModel.Season.WINTER })
            Text(text = "Winter")
        }
        Text(
            text = "Select scale",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row {
            RadioButton(
                selected = scaleType == ThermometerViewModel.ScaleType.CELSIUS,
                onClick = { scaleType = ThermometerViewModel.ScaleType.CELSIUS })
            Text(text = "Celsius")
            RadioButton(
                selected = scaleType == ThermometerViewModel.ScaleType.KELVIN,
                onClick = { scaleType = ThermometerViewModel.ScaleType.KELVIN })
            Text(text = "Kelvin")
            RadioButton(
                selected = scaleType == ThermometerViewModel.ScaleType.FAHRENHEIT,
                onClick = { scaleType = ThermometerViewModel.ScaleType.FAHRENHEIT })
            Text(text = "Fahrenheit")
        }
        Button(onClick = {
            if (isValidDouble(number))
                result = viewModel.processInput(number.toDouble(), season, scaleType)
        }) {
            Text(text = "Process input")
        }
        result?.let {
            Text(text = result!!)
        }
    }


}

@Preview
@Composable
fun ThermometerScreenPreview() {
    ThermometerScreen()
}