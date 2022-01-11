package com.example.base_library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  16:20 <br>
 * 版本： v1.0<br>
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {

    protected DB databinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, bindLayout(), container, false);
        return databinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intEnv();
        initView();
        initData();
    }

    protected abstract void intEnv();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int bindLayout();
}
