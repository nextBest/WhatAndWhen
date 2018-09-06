package com.nextbest.whatandwhen.mvp;

import com.nextbest.whatandwhen.interfaces.ItemSelected;

public abstract class BaseViewStatePresenter<VS, V extends ViewStateView<VS>> extends BasePresenter<V> {
    private VS viewState;

    public BaseViewStatePresenter() {
        viewState = createViewState();
    }

    public void renderNewViewState(ItemSelected<VS> viewStateAction) {
        viewStateAction.onItemSelected(viewState);
        ifViewAttached(view -> view.render(viewState));
    }

    protected abstract VS createViewState();

    public VS getViewState() {
        return viewState;
    }
}
