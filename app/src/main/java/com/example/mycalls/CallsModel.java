package com.example.mycalls;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallsModel implements Parcelable {


    @SerializedName("Id")
    @Expose
    public String id;
    @SerializedName("CagriId")
    @Expose
    public String cagriId;
    @SerializedName("TelNo")
    @Expose
    public String telNo;
    @SerializedName("Kullanici")
    @Expose
    public String kullanici;
    @SerializedName("Zaman")
    @Expose
    public String zaman;
    @SerializedName("Caldi")
    @Expose
    public String caldi;
    @SerializedName("Cevaplandi")
    @Expose
    public String cevaplandi;
    @SerializedName("Cekildi")
    @Expose
    public String cekildi;
    @SerializedName("Kapandi")
    @Expose
    public String kapandi;
    @SerializedName("Donuldu")
    @Expose
    public String donuldu;
    @SerializedName("Mesaj")
    @Expose
    public String mesaj;


    protected CallsModel(Parcel in) {
        id = in.readString();
        cagriId = in.readString();
        telNo = in.readString();
        kullanici = in.readString();
        zaman = in.readString();
        caldi = in.readString();
        cevaplandi = in.readString();
        cekildi = in.readString();
        kapandi = in.readString();
        donuldu = in.readString();
        mesaj=in.readString();

    }

    public CallsModel(String id, String cagriId, String telNo, String kullanici, String zaman, String caldi, String cevaplandi,String cekildi, String kapandi, String donuldu,String mesaj) {
        this.id=id;
        this.cagriId=cagriId;
        this.telNo=telNo;
        this.kullanici=kullanici;
        this.zaman=zaman;
        this.caldi=caldi;
        this.cevaplandi=cevaplandi;
        this.cekildi=cekildi;
        this.kapandi=kapandi;
        this.donuldu=donuldu;
        this.mesaj=mesaj;
    }
    public static final Creator<CallsModel> CREATOR = new Creator<CallsModel>() {
        @Override
        public CallsModel createFromParcel(Parcel in) {
            return new CallsModel(in);
        }

        @Override
        public CallsModel[] newArray(int size) {
            return new CallsModel[size];
        }
    };

    public static Creator<CallsModel> getCREATOR() {
        return CREATOR;
    }


    public String getCagriId() {
        return cagriId;
    }

    public void setCagriId(String cagriId) {
        this.cagriId = cagriId;
    }

    public String getCaldi() {
        return caldi;
    }

    public void setCaldi(String caldi) {
        this.caldi = caldi;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCevaplandi() {
        return cevaplandi;
    }

    public void setCevaplandi(String cevaplandi) {
        this.cevaplandi = cevaplandi;
    }

    public String getKullanici() {
        return kullanici;
    }

    public void setKullanici(String kullanici) {
        this.kullanici = kullanici;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getZaman() {
        return zaman;
    }

    public void setZaman(String zaman) {
        this.zaman = zaman;
    }


    public String getCekildi() {
        return cekildi;
    }


    public void setCekildi(String cekildi) {
        this.cekildi = cekildi;
    }


    public String getKapandi() {
        return kapandi;
    }

    public void setKapandi(String kapandi) {
        this.kapandi = kapandi;
    }


    public String getDonuldu() {
        return donuldu;
    }

    public void setDonuldu(String donuldu) {
        this.donuldu = donuldu;
    }

    public String getMesaj(){return mesaj;}
    public void setMesaj(String mesaj){this.mesaj=mesaj;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(kullanici);
        parcel.writeString(zaman);
        parcel.writeString(caldi);
        parcel.writeString(cekildi);
        parcel.writeString(cevaplandi);
        parcel.writeString(donuldu);
        parcel.writeString(telNo);
        parcel.writeString(kapandi);
        parcel.writeString(cagriId);
        parcel.writeString(id);
        parcel.writeString(mesaj);
    }
}
