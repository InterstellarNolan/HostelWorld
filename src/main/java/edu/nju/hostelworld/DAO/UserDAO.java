package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface UserDAO {
    public User get(int id);

    public User load(int id);

    public List<User> getByRestrictEqual(String column, Object value);

    public List<User> getByRestrictEqual(Map<String, Object> map);

    public int add(User user) throws Exception;

    public ResultMessage update(User user);
}
