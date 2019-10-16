package com.example.mycalls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MInterface {


    @GET("cagrilar?Islem=Baslik&Tarih=")
    Call<CallResult>  getCalls();


    @GET("cagrilar?")
    Call<CallResult>  getCallsWithDate(@Query("Islem") String process, @Query("Tarih") String date);

    @GET("cagrilar?Islem=Ayrinti&CagriId=94f04c55-bcf1-42b1-9390-a37367e6615e")
    Call<CallResult>  getInfo();

}
