package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.HostelDAO;
import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class HostelDAOImpl extends FatherDAOImpl implements HostelDAO {
    public Hostel get(int id) {
        return getEntity(Hostel.class, id);
    }

    public Hostel load(int id) {
        return loadProxy(Hostel.class, id);
    }

    public List<Hostel> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(Hostel.class, column, value);
    }

    public List<Hostel> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(Hostel.class, map);
    }

    public int add(Hostel hostel) throws Exception {
        return save(hostel);
    }

    public ResultMessage update(Hostel hostel) {
        return super.update(hostel);
    }
}
