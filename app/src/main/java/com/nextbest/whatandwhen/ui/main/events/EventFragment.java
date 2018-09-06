package com.nextbest.whatandwhen.ui.main.events;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.nextbest.whatandwhen.R;
import com.nextbest.whatandwhen.ui.base.BaseFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends BaseFragment<EventView, EventPresenter> implements EventView {


    @Inject
    EventPresenter eventPresenter;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public EventPresenter createPresenter() {
        return eventPresenter;
    }

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        return view;
    }

    @Override
    public void render(EventViewState viewState) {

    }

    @Override
    public Observable<Object> getButtonClick() {
        return RxView.clicks(new View(getContext()));
    }
}
