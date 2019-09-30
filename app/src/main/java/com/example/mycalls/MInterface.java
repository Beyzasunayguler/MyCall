package com.example.mycalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MInterface {
    @GET("http://192.168.2.156:10228/cagrilar?")
    Call<List<CallsModel>> getCalls();
}
