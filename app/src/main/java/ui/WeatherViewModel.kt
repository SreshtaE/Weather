package ui

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            val result = repository.fetchWeather(city)
            result.onSuccess { _weatherData.value = it }
            result.onFailure { /* Handle failure */ }
        }
    }
}
