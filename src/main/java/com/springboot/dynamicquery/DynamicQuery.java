package com.springboot.dynamicquery;

import java.util.List;

public interface DynamicQuery {
    public void save(Object entity);
    
    public void update(Object entity);
    
    public <T> void delete(Class<T> entityClass, Object entityid);

    public <T> void delete(Class<T> entityClass, Object[] entityids);
    
    /**
     * 查询对象列表，返回List
     * @param nativeSql
     * @param prarams
     * @return
     */
    <T>List<T> nativeQueryList(String nativeSql, Object... prarams);
    
    /**
     * 查询对象列表，返回List<Map<key, value>>
     * @param nativeSql
     * @param prarams
     * @return
     */
    <T>List<T> nativeQueryListMap(String nativeSql, Object... prarams);
    
    /**
     * 查询对象列表，返回List<组合对象>
     * @param resultClass
     * @param nativeSql
     * @param params
     * @return
     */
    <T>List<T> nativeQuetyListModel(Class<T> resultClass, String nativeSql, Object... params);
}