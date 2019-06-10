package com.softwaregiants.careertinder.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.CandidateListModel;
import com.softwaregiants.careertinder.models.GetCandidateDetailModel;
import com.softwaregiants.careertinder.models.JobOpeningsListModel;
import com.softwaregiants.careertinder.models.LoginSuccessModel;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private Context mContext;
    private ProgressDialog progressDialog;
    AlertDialog alertDialog;

    public static RetrofitClient getRetrofitClient(ApiResponseCallback mApiResponseCallBack, Context preferenceContext) {
        if ( retrofit == null ) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            // set your desired log level
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            String url = PreferenceManager.getInstance(preferenceContext.getApplicationContext()).getString(Constants.PK_CUSTOM_URL,Constants.BASE_URL);
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl( url )
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        RetrofitClient mRetrofitClient = new RetrofitClient();
        mRetrofitClient.mApiInterface = retrofit.create(ApiInterface.class);
        mRetrofitClient.mApiResponseCallBack = mApiResponseCallBack;
        mRetrofitClient.mContext = preferenceContext;
        return mRetrofitClient;
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (progressDialog != null)
            progressDialog.dismiss();
        if (alertDialog!=null)
            alertDialog.dismiss();
        if ( mApiResponseCallBack != null ) {
            if (response.isSuccessful()) {
                Log.d(TAG, response.toString());
                try {
                    String rawResponse = response.body().string();
                    Log.i(TAG, "onResponse: JSON\n\n" + rawResponse + "\n\n");
                    BaseBean baseBean = new Gson().fromJson(rawResponse, BaseBean.class);
                    if (baseBean.getStatusCode().equalsIgnoreCase(Constants.SC_SUCCESS)) {
                        if (baseBean.getApiMethod().contains(Constants.API_CREATE_CANDIDATE)) {
                            mApiResponseCallBack.onSuccess(baseBean);
                            return;
                        } else if (baseBean.getApiMethod().contains(Constants.API_GET_CANDIDATE_MATCHES)) {
                            CandidateListModel candidateListModel = new Gson().fromJson(rawResponse, CandidateListModel.class);
                            mApiResponseCallBack.onSuccess(candidateListModel);
                            return;
                        }
                        switch (baseBean.getApiMethod()) {
                            case Constants.API_METHOD_LOGIN: {
                                LoginSuccessModel loginSuccessModel = new Gson().fromJson(rawResponse, LoginSuccessModel.class);
                                mApiResponseCallBack.onSuccess(loginSuccessModel);
                                break;
                            }
                            case Constants.API_METHOD_SIGN_UP: {
                                mApiResponseCallBack.onSuccess(baseBean);
                                break;
                            }
                            case Constants.API_METHOD_ADD_NEW_JOB_OPENING: {
                                mApiResponseCallBack.onSuccess(baseBean);
                                break;
                            }
                            case Constants.API_METHOD_GET_JOB_OPENINGS:
                                JobOpeningsListModel jobOpeningsListModel = new Gson().fromJson(rawResponse, JobOpeningsListModel.class);
                                mApiResponseCallBack.onSuccess(jobOpeningsListModel);
                                break;
                            case Constants.API_METHOD_EDIT_JOB_OPENING:
                                mApiResponseCallBack.onSuccess(baseBean);
                                break;
                            case Constants.API_METHOD_GET_CANDIDATE_PROFILE:
                                GetCandidateDetailModel candidateProfileModel = new Gson().fromJson(rawResponse, GetCandidateDetailModel.class);
                                mApiResponseCallBack.onSuccess(candidateProfileModel);
                                break;
                            default: {
                                mApiResponseCallBack.onSuccess(baseBean);
                                break;
                            }
                        }
                    } else {
                        if (null != baseBean.getErrorMsg() && !baseBean.getErrorMsg().isEmpty()) {
                            Toast.makeText(mContext, baseBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, Constants.MSG_TECHNICAL_ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            } else {
                Toast.makeText(mContext, Constants.MSG_TECHNICAL_ERROR, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.e(TAG, t.toString());
        mApiResponseCallBack.onFailure(t);
        Toast.makeText(mContext,Constants.MSG_TECHNICAL_ERROR,Toast.LENGTH_SHORT).show();
        if (progressDialog != null)
            progressDialog.dismiss();
        if (alertDialog!=null)
            alertDialog.dismiss();
    }

    public void createProgressBar(Context context) {
        final View view = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.alert_progress, null);
        alertDialog = new AlertDialog.Builder(mContext).create();
        alertDialog.setCancelable(false);
//                    alertDialog.setMessage("New URL");


        alertDialog.setView(view);
        alertDialog.show();
    }

}
