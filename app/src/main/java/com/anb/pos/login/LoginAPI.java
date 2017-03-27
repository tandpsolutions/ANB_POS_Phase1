package com.anb.pos.login;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Bhaumik on 27/03/17.
 */

public interface LoginAPI {

    @FormUrlEncoded
    @POST("ValidateLogin")
    Call<JsonObject> validateLogin(@Field("username") String username, @Field("password") String password, @Field("branch_cd") String branch_cd);
}
