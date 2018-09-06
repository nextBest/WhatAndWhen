package com.nextbest.whatandwhen.ui.main.events;

import com.nextbest.whatandwhen.mvp.BaseViewStatePresenter;
import com.nextbest.whatandwhen.mvp.annotations.RunOnAttach;

public class EventPresenter extends BaseViewStatePresenter<EventViewState, EventView> {
    @Override
    protected EventViewState createViewState() {
        return new EventViewState();
    }


    @RunOnAttach
    public void listenCategoryClick() {
        ifViewAttached(view -> addSubscription(view.getButtonClick().subscribe(object ->{

                }
                , Throwable::printStackTrace)));
    }
}
