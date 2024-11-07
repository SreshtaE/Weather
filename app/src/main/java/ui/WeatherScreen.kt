package ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    var city by remember { mutableStateOf("") }
    val weather = viewModel.weatherData.observeAsState().value

    Column {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City") }
        )
        Button(onClick = { viewModel.fetchWeather(city) }) {
            Text("Search")
        }

        weather?.let {
            Text("City: ${it.name}")
            Text("Temperature: ${it.main.temp}°C")
            Text("Humidity: ${it.main.humidity}%")
            Image(
                painter = rememberImagePainter("http://openweathermap.org/img/wn/${it.weather[0].icon}@2x.png"),
                contentDescription = null
            )
        }
    }
}

