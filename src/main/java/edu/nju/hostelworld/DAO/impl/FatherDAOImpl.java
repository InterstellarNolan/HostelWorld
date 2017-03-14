package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.FatherDAO;
import edu.nju.hostelworld.util.ResultMessage;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class FatherDAOImpl implements FatherDAO {
    @Autowired
    protected SessionFactory sessionFactory;

    private Session getNewSession() {

        return sessionFactory.openSession();
    }

    private Session getCurrentSession() {
        Session session = sessionFactory.getCurrentSession();

        return session == null ? getNewSession() : session;
    }

    public int save(Object entity) throws Exception {
        Session session=getNewSession();
        Transaction tr=session.beginTransaction();
        try {
            int id=(Integer) session.save(entity);
            return id;
        }catch (Exception e){
            throw e;
        }finally {
            tr.commit();
            session.clear();
            session.close();
        }
    }

    public ResultMessage saveOrUpdate(Object entity) {
        Session session=getNewSession();
        Transaction tr=session.beginTransaction();
        try {
            session.saveOrUpdate(entity);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }finally {
            tr.commit();
            session.clear();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(Object entity) {
        Session session=getNewSession();
        Transaction tr=session.beginTransaction();
        try {
            session.merge(entity);
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAILURE;
        }finally {
            tr.commit();
            session.clear();
            session.close();
        }
        return ResultMessage.SUCCESS;
    }

    public <T> T getEntity(Class<T> c, int id) {
        Session session = sessionFactory.getCurrentSession();
        if (session != null) {
            session.clear(); // internal cache clear
        }

        Cache cache = sessionFactory.getCache();

        if (cache != null) {
            cache.evictAllRegions(); // Evict data from all query regions.
            cache.evictCollectionRegions();
            cache.evictDefaultQueryRegion();
            cache.evictEntityRegions();
            cache.evictQueryRegions();
            cache.evictNaturalIdRegions();
        }

        T entity= (T) session.get(c,id);
        return entity;
    }

    public <T> T loadProxy(Class<T> c, int id) {
        Session session=getNewSession();
        T entity= (T) session.load(c,id);
        return entity;
    }

    public <T> List<T> getAll(Class<T> c) {
        Session session=getNewSession();
        Criteria criteria=session.createCriteria(c);
        List list=criteria.list();
        return list;
    }

    public <T> List<T> getByRestrictEqual(Class<T> c, String column, Object value) {
        Session session=getNewSession();
        Criteria criteria=session.createCriteria(c);
        criteria.add(Restrictions.eq(column,value));
        return criteria.list();
    }

    public <T> List<T> getByRestrictEqual(Class<T> c,Map<String,Object> map){
        Session session=getNewSession();
        Criteria criteria=session.createCriteria(c);
        for(Map.Entry<String,Object> entry:map.entrySet()){
            criteria.add(Restrictions.eq(entry.getKey(),entry.getValue()));
        }
        return criteria.list();
    }
}
