package com.example.base_library;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  13:06 <br>
 * 版本： v1.0<br>
 */
public abstract class BaseMvvmActivity<DB extends ViewDataBinding,VM extends ViewModel> extends BaseActivity {

    protected DB databinding;
    protected VM viewModel;

    private HashMap<Integer,Object> map = new HashMap<>();

    @Override
    protected void initEnv() {
         databinding = DataBindingUtil.setContentView(this, bindLayout());
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

    protected abstract int bindLayout();
}
