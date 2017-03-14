package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.BookBill;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface BookBillDAO {
    public BookBill get(int id);
    public BookBill load(int id);
    public List<BookBill> getByRestrictEqual(String column, Object value);
    public List<BookBill> getByRestrictEqual(Map<String,Object> map);

    public int add(BookBill bookBill)throws Exception;
    public ResultMessage update(BookBill bookBill);
}
