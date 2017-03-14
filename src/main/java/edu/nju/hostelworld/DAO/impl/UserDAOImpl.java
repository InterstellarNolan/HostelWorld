package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.UserDAO;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class UserDAOImpl extends FatherDAOImpl implements UserDAO{
    public User get(int id) {
        return getEntity(User.class,id);
    }

    public User load(int id) {
        return loadProxy(User.class,id);
    }

    public List<User> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(User.class,column,value);
    }

    public List<User> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(User.class,map);
    }

    public int add(User user) throws Exception {
        return save(user);
    }

    public ResultMessage update(User user) {
        return super.update(user);
    }
}
