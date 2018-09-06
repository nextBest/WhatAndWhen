package com.nextbest.whatandwhen.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.nextbest.whatandwhen.interfaces.BackClicked;
import com.nextbest.whatandwhen.mvp.BasePresenter;

public abstract class BaseActivity<T extends MvpView, V extends BasePresenter<T>> extends MvpActivity<T, V> implements FragmentManager.OnBackStackChangedListener {

    protected Fragment actualFragment;
    protected Fragment lastFragment;

    @Override
    public void onBackStackChanged() {
        refreshActualFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideKeyboard();
        getSupportFragmentManager().removeOnBackStackChangedListener(this);
    }

    @NonNull
    @Override
    public V createPresenter() {
        return null;
    }

    public void setFragment(Fragment fragment) {
        if (getActualFragment() == null || !getActualFragment().getClass().getName().equals(fragment.getClass().getName())) {

            FragmentManager fragmentManager = getSupportFragmentManager();

            while (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStackImmediate();
            }
            //getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getSimpleName(), 0);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(getMainFragmentContainer().getId(), fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.commitAllowingStateLoss();

            setActualFragment(fragment);
        }
    }

    public void pushFragment(Fragment fragment) {
        if (getActualFragment() == null || !getActualFragment().getClass().getName().equals(fragment.getClass().getName())) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(getMainFragmentContainer().getId(), fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commitAllowingStateLoss();

            setActualFragment(fragment);
        }
    }

    public void addFragment(Fragment fragment) {
        if (getActualFragment() == null || !getActualFragment().getClass().getName().equals(fragment.getClass().getName())) {


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(getMainFragmentContainer().getId(), fragment, fragment.getClass().getSimpleName());
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commitAllowingStateLoss();

            setActualFragment(fragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (getActualFragment() != null && getActualFragment() instanceof BackClicked && ((BackClicked) getActualFragment()).onBackClick()) {
            return;
        }
        super.onBackPressed();
    }

    public Fragment getActualFragment() {
        return actualFragment;
    }

    public void setActualFragment(Fragment fragment) {
        lastFragment = actualFragment;
        this.actualFragment = fragment;
    }

    public abstract FrameLayout getMainFragmentContainer();

    public void refreshActualFragment() {
        int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
        if (index >= 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
            String tag = backEntry.getName();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment != null) {
                setActualFragment(fragment);
            } else {
                setActualFragment(null);
            }
        } else {
            setActualFragment(null);
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
