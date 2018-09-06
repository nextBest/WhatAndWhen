package com.nextbest.whatandwhen.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import com.nextbest.whatandwhen.ui.base.BaseActivity;
import com.nextbest.whatandwhen.ui.login.LoginActivity;
import com.nextbest.whatandwhen.ui.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public SplashPresenter createPresenter() {
        return splashPresenter;
    }

    @Override
    public FrameLayout getMainFragmentContainer() {
        return null;
    }

    @Override
    public void openLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void openMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}
