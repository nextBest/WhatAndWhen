package com.nextbest.whatandwhen.ui.base;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class BaseFragment<T extends MvpView, V extends MvpPresenter<T>> extends MvpFragment<T, V> {
}