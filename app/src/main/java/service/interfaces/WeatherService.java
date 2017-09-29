package service.interfaces;

import model.Channel;
import model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by manolofernandez on 9/25/17.
 */

public interface WeatherService {
    String BASE_URL = "https://query.yahooapis.com/v1/public/yql?q=";
    String SUB_URL = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text in (\"nome, ak\"))";
    String LAST_URL = "&format=json";
    String FULL_URL = BASE_URL + SUB_URL + LAST_URL;

    @GET("v1/public/yql")
    Call<Response> getWeather(@Query("q") String queryCities, @Query("format") String format);
}
