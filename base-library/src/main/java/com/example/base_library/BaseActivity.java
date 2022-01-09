package com.example.base_library;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  14:03 <br>
 * 版本： v1.0<br>
 */
public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    protected DB databinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initEnv();
        initView();
        initData();
    }

    protected void initEnv(){
        databinding = DataBindingUtil.setContentView(this, bindLayout());
    }

    protected abstract int bindLayout();

    protected abstract void initView();

    protected abstract void initData();
}
