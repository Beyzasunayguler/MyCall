package com.example.mycalls;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MInterface {
    @GET("cagrilar")
    Call<CallResult>  getCalls();
}
