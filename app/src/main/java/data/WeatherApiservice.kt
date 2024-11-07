package data


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiservice {
        @GET("data/2.5/weather")
        suspend fun getWeatherByCity(
            @Query("q") city: String,
            @Query("appid") apiKey: String
        ): Response<WeatherResponse>
    }

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideWeatherApi(): WeatherApiService = RetrofitInstance.api
}
