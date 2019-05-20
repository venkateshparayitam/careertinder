package com.softwaregiants.careertinder.networking;

import android.util.Log;

import com.google.gson.Gson;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.LoginSuccessModel;
import com.softwaregiants.careertinder.utilities.Constants;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements Callback<ResponseBody> {

    private static Retrofit retrofit;
    private ApiResponseCallback mApiResponseCallBack;
    public ApiInterface mApiInterface;
    private String TAG = RetrofitClient.class.getSimpleName();

    public static RetrofitClient getRetrofitClient(ApiResponseCallback mApiResponseCallBack) {
        if ( retrofit == null ) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RetrofitClient mRetrofitClient = new RetrofitClient();
        mRetrofitClient.mApiInterface = retrofit.create(ApiInterface.class);
        mRetrofitClient.mApiResponseCallBack = mApiResponseCallBack;
        return mRetrofitClient;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            Log.d(TAG, response.toString());
            try {
                String rawResponse = response.body().string();
                BaseBean baseBean = new Gson().fromJson( rawResponse, BaseBean.class);
                switch (baseBean.getApiMethod()) {
                    case Constants.API_METHOD_LOGIN: {
                        LoginSuccessModel loginSuccessModel = new Gson().fromJson(rawResponse, LoginSuccessModel.class);
                        mApiResponseCallBack.onSuccess(loginSuccessModel);
                        break;
                    }
                    case Constants.API_METHOD_SIGNUP: {
                        mApiResponseCallBack.onSuccess(baseBean);
                        break;
                    }
                    case Constants.API_METHOD_ADD_NEW_JOB_OPENING: {
                        mApiResponseCallBack.onSuccess(baseBean);
                        break;
                    }
                }
            } catch (Exception e) {
                //mApiResponseCallBack.onFailure(e);
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e(TAG, t.toString());
        mApiResponseCallBack.onFailure(t);
    }

}
