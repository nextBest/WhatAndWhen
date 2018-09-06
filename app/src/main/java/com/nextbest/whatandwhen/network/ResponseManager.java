package com.nextbest.whatandwhen.network;

import com.google.gson.Gson;
import com.nextbest.whatandwhen.network.model.ErrorModel;
import com.nextbest.whatandwhen.network.model.ErrorStatus;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.BiConsumer;
import retrofit2.HttpException;

abstract public class ResponseManager<T> implements BiConsumer<T, Throwable> {

    public abstract void success(T ret);

    public abstract void fail(int status, int errorMessage);

    @Override
    public void accept(T t, Throwable throwable) throws Exception {
        if (t != null) {
            success(t);
        } else {
            handleErrorResponse(throwable);
        }
    }

    /**
     * manage response
     *
     * @param throwable - Throwable
     */
    private void handleErrorResponse(Throwable throwable) {
        try {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                String responseString = httpException.response().errorBody().string();
                if (httpException.code() == ErrorStatus.HTTP_RESPONSE_STATUS_BAD_REQUEST) {
                    ErrorModel errorModel = new Gson().fromJson(responseString, ErrorModel.class);
                    if (errorModel != null) {
                        fail(errorModel.getError(), ErrorStatus.getErrorMessage(errorModel.getError()));
                    } else {
                        throwAppParseException(throwable);
                    }
                } else if (httpException.code() == ErrorStatus.HTTP_RESPONSE_STATUS_UNAUTHORIZED) {
                    //if status is unauthorized do nothing
                } else {
                    throwable.printStackTrace();
                    fail(ErrorStatus.SERVER_ERROR, ErrorStatus.getErrorMessage(ErrorStatus.SERVER_ERROR));
                }
            } else if (throwable instanceof SocketTimeoutException || throwable instanceof UnknownHostException) {
                fail(ErrorStatus.CONNECTION_TIMEOUT, ErrorStatus.getErrorMessage(ErrorStatus.CONNECTION_TIMEOUT));
            } else {
                throwAppParseException(throwable);
            }
        } catch (IOException e) {
            throwAppParseException(throwable);
        }
    }


    /**
     * throw app parse exception
     *
     * @param throwable - Throwable
     */
    private void throwAppParseException(Throwable throwable) {
        throwable.printStackTrace();
        fail(ErrorStatus.APP_PARSE_ERROR, ErrorStatus.getErrorMessage(ErrorStatus.APP_PARSE_ERROR));
    }
}
