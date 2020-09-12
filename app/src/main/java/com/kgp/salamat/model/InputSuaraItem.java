package com.kgp.salamat.model;

import com.google.gson.annotations.SerializedName;

public class InputSuaraItem {

    @SerializedName("no_paslon")
    public String no_paslon;
    @SerializedName("nama_paslon")
    public String nama_paslon;
    @SerializedName("nik_relawan")
    public String nik_relawan;
    @SerializedName("nama_relawan")
    public String nama_relawan;
    @SerializedName("id_tps")
    public String jumlah_suara;
    @SerializedName("url_bukti")
    public String url_bukti;

    public InputSuaraItem(String no_paslon, String nama_paslon, String nik_relawan, String nama_relawan, String jumlah_suara, String url_bukti) {
        this.no_paslon = no_paslon;
        this.nama_paslon = nama_paslon;
        this.nik_relawan = nik_relawan;
        this.nama_relawan = nama_relawan;
        this.jumlah_suara = jumlah_suara;
        this.url_bukti = url_bukti;
    }

    public String getNo_paslon() {
        return no_paslon;
    }

    public void setNo_paslon(String no_paslon) {
        this.no_paslon = no_paslon;
    }

    public String getNama_paslon() {
        return nama_paslon;
    }

    public void setNama_paslon(String nama_paslon) {
        this.nama_paslon = nama_paslon;
    }

    public String getNik_relawan() {
        return nik_relawan;
    }

    public void setNik_relawan(String nik_relawan) {
        this.nik_relawan = nik_relawan;
    }

    public String getNama_relawan() {
        return nama_relawan;
    }

    public void setNama_relawan(String nama_relawan) {
        this.nama_relawan = nama_relawan;
    }

    public String getJumlah_suara() {
        return jumlah_suara;
    }

    public void setJumlah_suara(String jumlah_suara) {
        this.jumlah_suara = jumlah_suara;
    }

    public String getUrl_bukti() {
        return url_bukti;
    }

    public void setUrl_bukti(String url_bukti) {
        this.url_bukti = url_bukti;
    }
}
