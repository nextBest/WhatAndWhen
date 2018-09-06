package com.nextbest.whatandwhen.data.repository;

import com.nextbest.whatandwhen.network.NetworkClient;

public class UserRepository {


    private NetworkClient networkClient;


    public UserRepository(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

//    public Single<Object> login(LoginModel loginModel) {
//        return restClient.getService().login(loginModel)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io());
//    }
}
