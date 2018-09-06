package com.nextbest.whatandwhen.mvp;

import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface ViewStateView <VS> extends MvpView {
    void render(VS viewState);
}
