package com.example.base_library;

import java.lang.reflect.Field;

/**
 * <p>描述：</p>
 * 作者： guai<br>
 * 日期：  16:38 <br>
 * 版本： v1.0<br>
 */
public class BaseRepository {
    public BaseRepository() {
    }

    private void inject(){
        Class<? extends BaseRepository> aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        if (declaredFields.length == 0){
            throw new RuntimeException("沒有匹配的成员变量");
        }

        boolean falg = false;

        for (Field declaredField : declaredFields) {
            Model annotation = declaredField.getAnnotation(Model.class);
            if (annotation != null){
                //设置为可用
                declaredField.setAccessible(true);

                falg = true;

                //获取变量类型的路径名
                String name = declaredField.getType().getName();

                try {
                    //获取字节码文件
                    Class<?> aClass1 = Class.forName(name);
                    //创建实例
                    Object o = aClass1.newInstance();
                    //赋值
                    declaredField.set(this,o);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!falg){
            throw new RuntimeException("没有model");
        }
    }
}
