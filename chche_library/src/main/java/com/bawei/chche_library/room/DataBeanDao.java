package com.bawei.chche_library.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Date:2022/1/11
 * Time:9:56
 * author:chenlanglang
 * Describe:
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
@Dao
public abstract class DataBeanDao<T> {

    private String simpleName;

    public DataBeanDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
        Class actualTypeArguments = (Class) genericSuperclass.getActualTypeArguments()[0];
        simpleName = actualTypeArguments.getSimpleName();
    }

    //??????
    public void intsertAll(T... all){
        insert(all);
    }
    @Insert
    protected abstract void insert(T... all);

    //??????
    public void upDataA(T... all){

    }
    protected abstract void upData(T... all);

    //??????
    public void deleteSingle(T... all){
        delete(all);
    }
    protected abstract void delete(T... all);

    //????????????
    @RawQuery
    protected abstract T find(SupportSQLiteQuery query);

    public T find(Long id){
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("SELECT * FROM" + simpleName + "WHERE id = ? ", new Long[]{});
        return find(simpleSQLiteQuery);
    }

    //????????????
    @RawQuery
    protected abstract Integer deleteAll(SupportSQLiteQuery query);
    public Integer deleteAll(){
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery(" DELETE FROM " + simpleName);
        return deleteAll(simpleSQLiteQuery);
    }

    //????????????
    @RawQuery
    protected abstract List<T> findAll(SupportSQLiteQuery query);

    public List<T> findAll(){
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery(" SELECT * FROM " + simpleName);
        return findAll(simpleSQLiteQuery);
    }


}
