package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.PayBill;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface PayBillDAO {
    public PayBill get(int id);

    public PayBill load(int id);

    public List<PayBill> getByRestrictEqual(String column, Object value);

    public List<PayBill> getByRestrictEqual(Map<String, Object> map);

    public int add(PayBill payBill) throws Exception;

    public ResultMessage update(PayBill payBill);
}
