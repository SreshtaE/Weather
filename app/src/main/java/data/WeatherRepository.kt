package data

import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: WeatherApiService
) {
    suspend fun fetchWeather(city: String): Result<WeatherResponse> {
        return try {
            val response = apiService.getWeatherByCity(city, API_KEY)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error fetching weather"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
