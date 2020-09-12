package com.kgp.salamat.api;

import com.kgp.salamat.model.ResponseDaftar;
import com.kgp.salamat.model.ResponseInputSuara;
import com.kgp.salamat.model.ResponseListPaslon;
import com.kgp.salamat.model.ResponseListRelawan;
import com.kgp.salamat.model.ResponseSpinnerTps;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //endpoint list relawan
    @GET(Api.ENDPOINT_LIST_RELAWAN)
    Call<ResponseListRelawan> getListRelawan();

    //endpoint list paslon
    @GET(Api.ENDPOINT_LIST_PASLON)
    Call<ResponseListPaslon> getListPaslon();

    @GET(Api.ENDPOINT_SPINNER_TPS)
    Call<ResponseSpinnerTps> getspinnertps();

    @FormUrlEncoded
    @POST(Api.ENDPOINT_REGISTER_RELAWAN)
    Call<ResponseDaftar> add(
            @Field("nik") String nik,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp,
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("tps") String tps
    );

    @FormUrlEncoded
    @POST(Api.ENDPOINT_INPUT_SUARA)
    Call<ResponseInputSuara> add(
            @Field("no_paslon") String no_paslon,
            @Field("nama_paslon") String nama_paslon,
            @Field("nik_relawan") String nik_relawan,
            @Field("nama_relawan") String nama_relawan,
            @Field("id_tps") String id_tps,
            @Field("nama_tps") String nama_tps,
            @Field("jumlah_suara") String jumlah_suara,
            @Field("url_bukti") String url_bukti
    );

}