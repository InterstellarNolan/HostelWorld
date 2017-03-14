package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface FatherDAO {
    public int save(Object entity) throws Exception;

    public ResultMessage saveOrUpdate(Object entity);

    public ResultMessage update(Object entity);

    public <T> T getEntity(Class<T> c,int id);

    public <T> T loadProxy(Class<T> c,int id);

    public <T> List<T> getAll(Class<T> c);

    public <T> List<T> getByRestrictEqual(Class<T> c,String column,Object value);
    /**
     * 表的列名与对应的值构成的map
     * @param c
     * @param map
     * @param <T>
     * @return
     */
    public <T> List<T> getByRestrictEqual(Class<T> c,Map<String,Object> map);
}
