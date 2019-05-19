package com.softwaregiants.careertinder.networking;

import com.softwaregiants.careertinder.activities.SignUp;
import com.softwaregiants.careertinder.models.AddJobOpeningModel;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.LoginModel;
import com.softwaregiants.careertinder.models.LoginSuccessModel;
import com.softwaregiants.careertinder.models.PostSignUpModel;
import com.softwaregiants.careertinder.models.SignUpModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("5cd0aab83300006200b12587")
    Call<BaseBean> getSuccessCode();

    @POST("api/signUp")
    Call<ResponseBody> signUp(@Body SignUpModel signUpModel);

    @POST("api/login")
    Call<ResponseBody> login(@Body LoginModel loginModel);

    @POST("api/postSignUp")
    Call<ResponseBody> postSignUp(@Body PostSignUpModel postSignUpModel);

    @POST("api/createProfileCompany/{url}")
    Call<ResponseBody> addNewJobOpening(@Body AddJobOpeningModel addJobOpeningModel, @Path("url") String auth_code);
}
