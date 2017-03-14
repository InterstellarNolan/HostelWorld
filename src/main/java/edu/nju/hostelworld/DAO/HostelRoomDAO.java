package edu.nju.hostelworld.DAO;

import edu.nju.hostelworld.entity.HostelRoom;
import edu.nju.hostelworld.util.ResultMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface HostelRoomDAO {
    public HostelRoom get(int id);

    public HostelRoom load(int id);

    public List<HostelRoom> getByRestrictEqual(String column, Object value);

    public List<HostelRoom> getByRestrictEqual(Map<String, Object> map);

    public int add(HostelRoom room) throws Exception;

    public ResultMessage add(List<HostelRoom> rooms);

    public ResultMessage update(HostelRoom room);
}
