package com.example.mycalls;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("cagrilar")
    @Expose
    public List<CallsModel> cagrilar = null;

}