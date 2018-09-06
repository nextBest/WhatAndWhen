package com.nextbest.whatandwhen.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter <V extends MvpView> extends MvpBasePresenter<V> {

    private CompositeDisposable disposable;

    public BasePresenter() {
       disposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        disposable.dispose();
        super.detachView();
    }

    protected void addSubscription(Disposable disposable) {
        this.disposable.add(disposable);
    }
}
