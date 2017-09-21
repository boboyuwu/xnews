package com.boboyuwu.common.loadandretrymanager;

import android.view.View;

public abstract class OnLoadingAndRetryListener
{
    public abstract void setRetryEvent(View retryView);

    public void setLoadingEvent(View loadingView)
    {
    }

    public void setEmptyEvent(View emptyView)
    {
    }

    public int generateLoadingLayoutId()
    {
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }

    public int generateRetryLayoutId()
    {
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }

    public int generateEmptyLayoutId()
    {
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }

    public View generateLoadingLayout()
    {
        return null;
    }

    public View generateRetryLayout()
    {
        return null;
    }

    public View generateEmptyLayout()
    {
        return null;
    }

    public boolean isSetLoadingLayout()
    {
        return generateLoadingLayoutId() != LoadingAndRetryManager.NO_LAYOUT_ID || generateLoadingLayout() != null;
    }

    public boolean isSetRetryLayout()
    {
        return generateRetryLayoutId() != LoadingAndRetryManager.NO_LAYOUT_ID || generateRetryLayout() != null;
    }

    public boolean isSetEmptyLayout()
    {
        return generateEmptyLayoutId() != LoadingAndRetryManager.NO_LAYOUT_ID || generateEmptyLayout() != null;
    }


}