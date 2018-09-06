package com.nextbest.whatandwhen.ui.main.events;

import com.nextbest.whatandwhen.mvp.ViewStateView;

import io.reactivex.Observable;

public interface EventView extends ViewStateView<EventViewState> {

    Observable<Object> getButtonClick();
}
