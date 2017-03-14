package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.ManagerDAO;
import edu.nju.hostelworld.entity.Manager;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/12.
 */
@Repository
public class ManagerDAOImpl extends FatherDAOImpl implements ManagerDAO {
    @Override
    public Manager get(int id) {
        return getEntity(Manager.class, id);
    }

    @Override
    public Manager load(int id) {
        return loadProxy(Manager.class, id);
    }

    @Override
    public List<Manager> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(Manager.class, column, value);
    }

    @Override
    public List<Manager> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(Manager.class, map);
    }

    @Override
    public int add(Manager member) throws Exception {
        return save(member);
    }

    @Override
    public ResultMessage update(Manager member) {
        return super.update(member);
    }
}
