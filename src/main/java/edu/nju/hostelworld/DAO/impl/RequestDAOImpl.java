package edu.nju.hostelworld.DAO.impl;

import edu.nju.hostelworld.DAO.RequestDAO;
import edu.nju.hostelworld.entity.RequestModify;
import edu.nju.hostelworld.entity.RequestOpen;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/11.
 */
@Repository
public class RequestDAOImpl extends FatherDAOImpl implements RequestDAO {

    @Override
    public RequestOpen getOpenRequest(int id) {
        return getEntity(RequestOpen.class, id);
    }

    @Override
    public RequestOpen loadOpenRequest(int id) {
        return loadProxy(RequestOpen.class, id);
    }

    @Override
    public List<RequestOpen> getOpenRequestByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(RequestOpen.class, column, value);
    }

    @Override
    public List<RequestOpen> getOpenRequestByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(RequestOpen.class, map);
    }

    @Override
    public int addOpenRequest(RequestOpen requestOpen) throws Exception {
        return save(requestOpen);
    }

    @Override
    public ResultMessage updateOpenRequest(RequestOpen requestOpen) {
        return update(requestOpen);
    }

    @Override
    public RequestModify getModifyRequest(int id) {
        return getEntity(RequestModify.class, id);
    }

    @Override
    public RequestModify loadModifyRequest(int id) {
        return loadProxy(RequestModify.class, id);
    }

    @Override
    public List<RequestModify> getModifyRequestByRestrictEqual(String column, Object value) {
        return getByRestrictEqual(RequestModify.class, column, value);
    }

    @Override
    public List<RequestModify> getModifyRequestByRestrictEqual(Map<String, Object> map) {
        return getByRestrictEqual(RequestModify.class, map);
    }

    @Override
    public int addModifyRequest(RequestModify requestModify) throws Exception {
        return save(requestModify);
    }

    @Override
    public ResultMessage updateModifyRequest(RequestModify requestModify) {
        return update(requestModify);
    }
}
