package com.bawei.chche_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import androidx.annotation.IdRes;

/**
 * Date:2022/1/8
 * Time:10:18
 * author:chenlanglang
 * Describe:   Sp单例存储
 * <p>
 * .----------------.  .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .-----------------. .----------------.
 * | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
 * | |     ______   | || |  ____  ____  | || |  _________   | || | ____  _____  | || |   _____      | || |      __      | || | ____  _____  | || |    ______    | || |   _____      | || |      __      | || | ____  _____  | || |    ______    | |
 * | |   .' ___  |  | || | |_   ||   _| | || | |_   ___  |  | || ||_   \|_   _| | || |  |_   _|     | || |     /  \     | || ||_   \|_   _| | || |  .' ___  |   | || |  |_   _|     | || |     /  \     | || ||_   \|_   _| | || |  .' ___  |   | |
 * | |  / .'   \_|  | || |   | |__| |   | || |   | |_  \_|  | || |  |   \ | |   | || |    | |       | || |    / /\ \    | || |  |   \ | |   | || | / .'   \_|   | || |    | |       | || |    / /\ \    | || |  |   \ | |   | || | / .'   \_|   | |
 * | |  | |         | || |   |  __  |   | || |   |  _|  _   | || |  | |\ \| |   | || |    | |   _   | || |   / ____ \   | || |  | |\ \| |   | || | | |    ____  | || |    | |   _   | || |   / ____ \   | || |  | |\ \| |   | || | | |    ____  | |
 * | |  \ `.___.'\  | || |  _| |  | |_  | || |  _| |___/ |  | || | _| |_\   |_  | || |   _| |__/ |  | || | _/ /    \ \_ | || | _| |_\   |_  | || | \ `.___]  _| | || |   _| |__/ |  | || | _/ /    \ \_ | || | _| |_\   |_  | || | \ `.___]  _| | |
 * | |   `._____.'  | || | |____||____| | || | |_________|  | || ||_____|\____| | || |  |________|  | || ||____|  |____|| || ||_____|\____| | || |  `._____.'   | || |  |________|  | || ||____|  |____|| || ||_____|\____| | || |  `._____.'   | |
 * | |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |
 * | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 * '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
 */
public class SharedPreferencesManager {

    public static volatile SharedPreferencesManager manager;

    public static SharedPreferencesManager getManager() {
        if (manager == null){
            //同步锁
            synchronized (SharedPreferencesManager.class){
                if (manager == null){
                    manager = new SharedPreferencesManager();
                }
            }
        }
        return manager;
    }


    //存值  参数一:上下文   参数二：键   参数三 ： 值 参数四：文件名
    public void putValue(Context context,String key,Object value,String name){
        //通过值获取名
        String simpleName = value.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        //判断各种类型
        if ("Integer".equals(simpleName)){
            edit.putInt(key, (Integer) value);
        }else if ("Float".equals(simpleName)){
            edit.putFloat(key, (Float) value);
        }else if ("Boolean".equals(simpleName)){
            edit.putBoolean(key, (Boolean) value);
        }else if ("String".equals(simpleName)){
            edit.putString(key, (String) value);
        }else if ("Long".equals(simpleName)){
            edit.putLong(key, (Long) value);
        }
        edit.apply();
    }

    //取值
    public Object getValue(Context context,String key,Object value,String name){

        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String simpleName = value.getClass().getSimpleName();

        if ("Integer".equals(simpleName)){
            return sharedPreferences.getInt(key, (Integer) value);
        }else if ("Float".equals(simpleName)){
            return sharedPreferences.getFloat(key, (Float) value);
        }else if ("Boolean".equals(simpleName)){
            return sharedPreferences.getBoolean(key, (Boolean) value);
        }else if ("String".equals(simpleName)){
            return sharedPreferences.getString(key, (String) value);
        }else if ("Long".equals(simpleName)){
            return sharedPreferences.getLong(key, (Long) value);
        }

        return null;
    }
}
