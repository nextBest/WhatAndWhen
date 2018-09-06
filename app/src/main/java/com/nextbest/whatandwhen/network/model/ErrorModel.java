package com.nextbest.whatandwhen.network.model;

import com.google.gson.annotations.SerializedName;

public class ErrorModel {

    @SerializedName("error")
    private int error;
    @SerializedName("error_message")
    private String errorMessage;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
