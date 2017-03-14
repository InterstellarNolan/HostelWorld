package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.Manager;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface ManagerDAO {
    public Manager get(int id);

    public Manager load(int id);

    public List<Manager> getByRestrictEqual(String column, Object value);

    public List<Manager> getByRestrictEqual(Map<String, Object> map);

    public int add(Manager member) throws Exception;

    public ResultMessage update(Manager member);
}
