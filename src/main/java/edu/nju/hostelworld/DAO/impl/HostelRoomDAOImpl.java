package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.HostelRoomDAO;
import edu.nju.hostelworld.entity.HostelRoom;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class HostelRoomDAOImpl extends FatherDAOImpl implements HostelRoomDAO {
    public HostelRoom get(int id) {
        return getEntity(HostelRoom.class, id);
    }

    public HostelRoom load(int id) {
        return loadProxy(HostelRoom.class, id);
    }

    public List<HostelRoom> getByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(HostelRoom.class, column, value);
    }

    public List<HostelRoom> getByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(HostelRoom.class, map);
    }

    public int add(HostelRoom room) throws Exception {
        return save(room);
    }

    public ResultMessage add(List<HostelRoom> rooms) {
        for (HostelRoom room : rooms) {
            try {
                save(room);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMessage.FAILURE;
            }
        }
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(HostelRoom room) {
        return update(room);
    }
}
