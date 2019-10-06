package com.example.mycalls;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MInterface {
    @GET("cagrilar?Islem=Baslik")
    Call<CallResult>  getCalls();

    @GET("cagrilar?Islem=Ayrinti&CagriId=94f04c55-bcf1-42b1-9390-a37367e6615e")
    Call<CallResult>  getInfo();

}
