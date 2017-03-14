package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface HostelDAO {
    public Hostel get(int id);

    public Hostel load(int id);

    public List<Hostel> getByRestrictEqual(String column, Object value);

    public List<Hostel> getByRestrictEqual(Map<String, Object> map);

    public int add(Hostel hostel) throws Exception;

    public ResultMessage update(Hostel hostel);
}
