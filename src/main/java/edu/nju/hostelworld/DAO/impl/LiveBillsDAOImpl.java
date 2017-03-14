package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.LiveBillDAO;
import edu.nju.hostelworld.entity.LiveBill;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class LiveBillsDAOImpl extends FatherDAOImpl implements LiveBillDAO {
    public LiveBill get(int id) {
        return getEntity(LiveBill.class, id);
    }

    public LiveBill load(int id) {
        return loadProxy(LiveBill.class, id);
    }

    public List<LiveBill> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(LiveBill.class, column, value);
    }

    public List<LiveBill> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(LiveBill.class, map);
    }

    public int add(LiveBill liveBill) throws Exception {
        return save(liveBill);
    }

    public ResultMessage update(LiveBill liveBill) {
        return update(liveBill);
    }
}
