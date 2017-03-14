package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.PayBillDAO;
import edu.nju.hostelworld.entity.PayBill;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class PayBillsDAOImpl extends FatherDAOImpl implements PayBillDAO {
    public PayBill get(int id) {
        return getEntity(PayBill.class,id);
    }

    public PayBill load(int id) {
        return loadProxy(PayBill.class,id);
    }

    public List<PayBill> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(PayBill.class,column,value);
    }

    public List<PayBill> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(PayBill.class,map);
    }

    public int add(PayBill payBill) throws Exception {
        return save(payBill);
    }

    public ResultMessage update(PayBill payBill) {
        return update(payBill);
    }
}
