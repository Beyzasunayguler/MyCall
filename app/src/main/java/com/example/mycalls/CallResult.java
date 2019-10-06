package com.example.mycalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CallResult {

    @SerializedName("cagrilar")
    @Expose
    public List<CallsModel> calls = null;

    public List<CallsModel> getCalls() {
        return calls;
    }

    public void setCalls(List<CallsModel> calls) {
        this.calls = calls;
    }

    @SerializedName("ayrıntılar")
    @Expose
    public List<CallsModel> infos = null;
    public List<CallsModel> getInfos() {
        return infos;
    }
    public void setInfos (List<CallsModel> infos) {
        this.infos = infos;
    }

}

