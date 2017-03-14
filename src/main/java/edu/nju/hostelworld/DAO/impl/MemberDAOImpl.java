package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.MemberDAO;
import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class MemberDAOImpl extends FatherDAOImpl implements MemberDAO {

    public Member get(int id) {
        return getEntity(Member.class, id);
    }

    public Member load(int id) {
        return loadProxy(Member.class, id);
    }

    public List<Member> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(Member.class, column, value);
    }

    public List<Member> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(Member.class, map);
    }

    public int add(Member member) throws Exception {
        return save(member);
    }

    public ResultMessage update(Member member) {
        return update(member);
    }

    public List<Member> getAll() {
        return getAll(Member.class);
    }
}
