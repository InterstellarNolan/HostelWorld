package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.BookBillDAO;
import edu.nju.hostelworld.entity.BookBill;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class BookBillsDAOImpl extends FatherDAOImpl implements BookBillDAO {
    public BookBill get(int id) {
        return getEntity(BookBill.class, id);
    }

    public BookBill load(int id) {
        return loadProxy(BookBill.class, id);
    }

    public List<BookBill> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(BookBill.class, column, value);
    }

    public List<BookBill> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(BookBill.class, map);
    }

    public int add(BookBill bookBill) throws Exception {
        return save(bookBill);
    }

    public ResultMessage update(BookBill bookBill) {
        return super.update(bookBill);
    }
}
