package com.softwaregiants.careertinder.networking;

import com.softwaregiants.careertinder.activities.SignUp;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.SignUpModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("5cd0aab83300006200b12587")
    Call<BaseBean> getSuccessCode();

    @POST("signup")
    Call<BaseBean> signUp(@Body SignUpModel signUpModel);

}
