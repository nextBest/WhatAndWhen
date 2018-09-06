package com.nextbest.whatandwhen.network.model;

import com.nextbest.whatandwhen.R;

public class ErrorStatus {

    public static final int HTTP_RESPONSE_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_RESPONSE_STATUS_UNAUTHORIZED = 401;

    public static final int CONNECTION_TIMEOUT = -1;
    public static final int SERVER_ERROR = -2;
    public static final int APP_PARSE_ERROR = -3;

    public static int getErrorMessage(int errorStatus) {
        switch (errorStatus) {
            case CONNECTION_TIMEOUT:
            case SERVER_ERROR:
            case APP_PARSE_ERROR:
                return R.string.error;
            default:
                return R.string.error;
        }
    }
}
