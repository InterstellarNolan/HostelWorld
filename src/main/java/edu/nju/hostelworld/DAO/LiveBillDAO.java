package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.LiveBill;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface LiveBillDAO {
    public LiveBill get(int id);

    public LiveBill load(int id);

    public List<LiveBill> getByRestrictEqual(String column, Object value);

    public List<LiveBill> getByRestrictEqual(Map<String, Object> map);

    public int add(LiveBill liveBill) throws Exception;

    public ResultMessage update(LiveBill liveBill);
}
