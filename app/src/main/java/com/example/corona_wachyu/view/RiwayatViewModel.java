package com.example.corona_wachyu.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.corona_wachyu.api.ApiEndPoint;
import com.example.corona_wachyu.api.ApiService;
import com.example.corona_wachyu.model.CountryModel;
import com.example.corona_wachyu.model.RiwayatModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RiwayatViewModel extends ViewModel {

    private MutableLiveData<ArrayList<RiwayatModel>> mutableLiveData = new MutableLiveData<>();

    public void setTodayData() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndpoint = retrofit.create(ApiEndPoint.class);

        Call<List<RiwayatModel>> call = apiEndpoint.getHistoryList(getFormattedDate());
        call.enqueue(new Callback<List<RiwayatModel>>() {
            @Override
            public void onResponse(Call<List<RiwayatModel>> call, Response<List<RiwayatModel>> response) {
                mutableLiveData.setValue((ArrayList<RiwayatModel>) response.body());
            }

            @Override
            public void onFailure(Call<List<RiwayatModel>> call, Throwable t) {

            }
        });
    }

    private String getFormattedDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        return simpleDateFormat.format(yesterday());
    }

    // fetch hari kemarin
    private Date yesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public LiveData<ArrayList<RiwayatModel>> getTodayListData() {
        return mutableLiveData;
    }
}
