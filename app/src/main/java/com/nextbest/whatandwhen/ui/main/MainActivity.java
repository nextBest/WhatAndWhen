package com.nextbest.whatandwhen.ui.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.nextbest.whatandwhen.R;
import com.nextbest.whatandwhen.ui.base.BaseActivity;
import com.nextbest.whatandwhen.ui.main.events.EventFragment;
import com.nextbest.whatandwhen.ui.splash.SplashPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<MainView,MainPresenter> implements MainView, HasSupportFragmentInjector {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pushFragment(new EventFragment());
    }

    @Override
    public FrameLayout getMainFragmentContainer() {
        return fragmentContainer;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return mainPresenter;
    }
}
