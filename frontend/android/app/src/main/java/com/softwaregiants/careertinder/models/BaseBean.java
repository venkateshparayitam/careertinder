package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

public class BaseBean {

    @SerializedName("status_code")
    String statusCode;

    @SerializedName("method")
    String apiMethod;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String status) {
        this.statusCode = statusCode;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }
}
