package com.example.tugasjson.api;


import com.example.tugasjson.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar(@Field("nim") String nim,
                       @Field("nama") String nama,
                       @Field("jurusan") String jurusan,
                       @Field("jk") String jk);



}
