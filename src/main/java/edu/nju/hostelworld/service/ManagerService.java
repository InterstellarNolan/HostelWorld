package edu.nju.hostelworld.service;

import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.util.ResultMessage;
import vo.IncomeVO;
import vo.LiveInNumVO;

import java.util.List;

/**
 * Created by Administrator on 17/3/2.
 */
public interface ManagerService {
    /**
     * 初始化
     *
     * @param managerId
     * @return
     */
    public ResultMessage init(int managerId);

    /**

     * 返回所有未审批的开店申请
     *
     * @return
     */
    public List<RequestOpen> getOpenRequests();

    /**

     * 返回所有未审批的店信息修改申请
     *
     * @return
     */
    public List<RequestModify> getModifyRequests();

    /**
     * 总经理审批收到的开店申请
     *
     * @param requestId
     * @param requestState
     * @return FAILURE, SUCCESS
     */
    public ResultMessage updateOpenRequest(int requestId, String requestState);

    /**
     * 总经理审批收到的店信息更改申请
     *
     * @param requestId
     * @param requestState
     * @return FAILURE, SUCCESS
     */
    public ResultMessage updateModifyRequest(int requestId, String requestState);

    /**
     * 总经理结算

     *
     * @param managerId
     * @param bankPassword
     * @return WRONG_PASSWORD, FAILURE, SUCCESS
     *
     */
    public ResultMessage count(int managerId, String bankPassword);


    /**
     * 获取所有通过审批的客栈
     *
     * @return
     */
    public List<Hostel> getAllPermittedHostels();

    /**
     * 获取所有会员
     * 包含了每个会员的住店、预订、消费的所有记录
     */
    public List<Member> getAllMembers();

    /**
     * 获取所有客栈的收入金额
     *
     * @return
     */
    public List<IncomeVO> getHostelIncomes();

    /**
     * 获取所有客栈的住店人数
     *
     * @return
     */
    public List<LiveInNumVO> getLiveInNums();

    /**
     * 获得当前经理实体
     *
     * @param managerId
     * @return
     */
    public Manager getById(int managerId);

    /**
     * 不区分类别
     *
     * @return
     */
    public List<RequestOpen> getAllOpenRequests();

    /**
     * @param id
     * @return
     */
    public RequestModify getModify(int id);

    public RequestOpen getOpen(int id);
}