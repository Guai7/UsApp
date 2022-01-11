package com.example.base_library;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  16:35 <br>
 * 版本： v1.0<br>
 */
public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel implements LifecycleObserver {

    protected Repo mRepository;
    private LifecycleOwner owner;

    public BaseViewModel(LifecycleOwner owner) {
        this.owner = owner;
        mRepository = createRepository();
        owner.getLifecycle().addObserver(this);
    }

    protected abstract Repo createRepository();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void uiConnect(){
        initResource();
    }

    //初始化资源
    protected abstract void initResource();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void disConnect(){
        releaseResources();
        owner.getLifecycle().removeObserver(this);
    }

    protected abstract void releaseResources();
}
