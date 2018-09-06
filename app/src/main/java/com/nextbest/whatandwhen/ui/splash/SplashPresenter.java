package com.nextbest.whatandwhen.ui.splash;

import com.nextbest.whatandwhen.mvp.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashView> {


    @Override
    public void attachView(SplashView view) {
        super.attachView(view);

        if (isUserLogged()) {
            openMainScreen();
        } else {
            openLoginScreen();
        }

    }


    private boolean isUserLogged() {
        return true;
    }

    private void openMainScreen() {
        ifViewAttached(SplashView::openMainScreen);
    }

    private void openLoginScreen() {
        ifViewAttached(SplashView::openLoginScreen);
    }
}
