package fr.esiea.ex4A.lovemeet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Map;

public interface AgifyClient {
    String BASE_URI = "https://api.agify.io";

    @GET("/")
    Call<Map<String, String>> getAge(@Query("name") String name, @Query("country_id") String countryId);
}
