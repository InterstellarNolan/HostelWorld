package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface MemberDAO {
    public Member get(int id);

    public Member load(int id);

    public List<Member> getByRestrictEqual(String column, Object value);

    public List<Member> getByRestrictEqual(Map<String, Object> map);

    public  List<Member> getAll();

    public int add(Member member) throws Exception;

    public ResultMessage update(Member member);
}
