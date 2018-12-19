package com.springboot.dynamicquery.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.springboot.dynamicquery.DynamicQuery;

@Repository
public class DynamicQueryImpl implements DynamicQuery {
    Logger logger = LoggerFactory.getLogger(DynamicQueryImpl.class);

    @PersistenceContext
    private EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void save(Object entity) {
        // TODO Auto-generated method stub
        em.persist(entity);
    }

    @Override
    public void update(Object entity) {
        // TODO Auto-generated method stub
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object entityid) {
        // TODO Auto-generated method stub
        delete(entityClass, new Object[] { entityid });
        
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        // TODO Auto-generated method stub
        for (Object id : entityids) {
            em.remove(em.getReference(entityClass, id));
        }
        
    }

    public Query createNativeQuery(String sql ,Object... params) {
        Query q = em.createNativeQuery(sql);
        if(params != null) {
            if(params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    q.setParameter(i + 1, params[i]);
                }
            }
        }
        return q;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> nativeQueryList(String nativeSql, Object... params) {
        // TODO Auto-generated method stub
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.TO_LIST);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> nativeQueryListMap(String nativeSql, Object... params) {
        // TODO Auto-generated method stub
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> nativeQuetyListModel(Class<T> resultClass, String nativeSql, Object... params) {
        // TODO Auto-generated method stub
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }
    
}