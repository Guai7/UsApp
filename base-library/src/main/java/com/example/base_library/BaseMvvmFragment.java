package com.example.base_library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  16:30 <br>
 * 版本： v1.0<br>
 */
public abstract class BaseMvvmFragment<DB extends ViewDataBinding,VM extends ViewModel> extends BaseFragment<DB> {

    protected VM viewModel;
    private HashMap<Integer,Object> map = new HashMap<>();

    @Override
    protected void intEnv() {

        databinding.setLifecycleOwner(this);

        viewModel = createViewModel();

        setVar(databinding,map);
    }

    /**
     * for循环给binding设置变量的值 hashMap的键内存(RB.x)类型的名称 值存值
     */
    protected void setVar(DB databinding, HashMap<Integer,Object> map){
        for (Map.Entry<Integer,Object> integerObjectEntry : map.entrySet()) {
            databinding.setVariable(integerObjectEntry.getKey(),integerObjectEntry.getValue());
        }
    }

    protected abstract VM createViewModel();
}
