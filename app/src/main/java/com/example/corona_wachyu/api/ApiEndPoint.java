package com.example.corona_wachyu.api;

import com.example.corona_wachyu.model.CountryModel;
import com.example.corona_wachyu.model.RingkasanModel;
import com.example.corona_wachyu.model.RiwayatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndPoint {
    @GET(Api.END_POINT_WORLD_HISTORY)
    Call<List<RiwayatModel>> getHistoryList(@Path("date") String date);

    @GET(Api.END_POINT_SUMMARY_WORLD)
    Call<RingkasanModel> getSummaryWorld();

    @GET(Api.END_POINT_IDN)
    Call<CountryModel> getSummaryIdn();
}
