package edu.nju.hostelworld.service;

import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.util.ResultMessage;
import vo.*;

import java.util.List;

/**
 * Created by Administrator on 17/3/2.
 */

public interface HostelService {


    /**
     * 初始化
     *
     * @return : REMIND_REQUEST,REQUEST_DENIED,REQUEST_UNCHECKED,SUCCESS;
     */
    public ResultMessage init(int hostelId);

    /**
     * 删除客栈
     *
     * @param hostelId
     * @return
     */
    public ResultMessage delete(int hostelId);

    /**
     * 客栈向总经理发出开店申请
     *
     * @param hostelId
     * @return SUCCESS, FAILURE
     */
    public ResultMessage requestManager(int hostelId);

    /**
     * 根据客栈ID获得客栈数据：
     *
     * @param hostelId
     * @return SUCCESS, FAILURE
     */
    public Hostel getById(int hostelId);

    /**
     * 修改客栈的基本信息
     *
     * @param
     * @return SUCCESS, FAILURE
     */
    public ResultMessage update(int id, String name, String address, String phone);

    /**
     * 登记住户的结账
     *
     * @param payVO
     * @return 实际顾客应支付的值or-1
     */
    public double enrollPay(PayVO payVO);

    /**
     * 会员选择用会员卡支付的时候，店员会选择这个服务。
     *
     * @param vipId
     * @param money
     * @return NOT_ENOUGH_MONEY, SUCCESS, FAILURE
     */
    public ResultMessage vipPay(int vipId, double money);

    /**
     * 非会员结账的时候 或会员选择不用会员卡支付的时候
     *
     * @param money
     * @return SUCCESS, FAILURE
     */
    public ResultMessage unVipPay(double money);

    /**
     * 登记住户的入店信息：
     *
     * @param liveInVO
     * @return SUCCESS, FAILURE
     */
    public ResultMessage liveIn(CheckInVO liveInVO);

    /**
     * 登记住户的离店信息:
     *
     * @param liveOutVO
     * @return SUCCESS, FAILURE
     */
    public ResultMessage depart(CheckOutVO liveOutVO);

    /**
     * @param hostelId
     * @param roomVOs
     * @return SUCCESS, FAILURE
     */
    public ResultMessage addRoom(int hostelId, List<HostelRoomVO> roomVOs);

    /**
     * @param hostelId
     * @param roomVO
     * @return SUCCESS, FAILURE
     */
    public ResultMessage addRoom(int hostelId, HostelRoomVO roomVO);

    /**
     * 客栈更新房间
     *
     * @param roomId
     * @param roomVO
     * @return SUCCESS, FAILURE
     */
    public ResultMessage updateRoom(int roomId, RoomVO_input roomVO);

    /**
     * 获取本店预订数据，包括预订和取消预订
     *
     * @param hostelId
     * @return
     */
    public List<BookBill> getAllBookBills(int hostelId);

    /**
     * 获取本店财务情况
     *
     * @param hostelId
     * @return
     */
    public List<PayBill> getAllPayBills(int hostelId);

    /**
     * 获取本店总收入
     *
     * @param hostelId
     * @return
     */
    public double getIncome(int hostelId);

    /**
     * 获取本店住店数据，包含入店、离店信息
     *
     * @param hostelId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int hostelId);

    /**
     * 得到该客栈的所有房间
     *
     * @param hostelId
     * @return
     */
    public List<HostelRoom> getAllRooms(int hostelId);

    /**
     * 只获得现在在市场上的房间
     *
     * @param hostelId
     * @return
     */
    public List<HostelRoom> getAllValidRooms(int hostelId);

    /**
     * 按照房间的id得到房间
     *
     * @param roomId
     * @return
     */
    public HostelRoom getRoomById(int roomId);

    /**
     * 返回所有通过总经理审批的客栈
     *
     * @return
     */
    public List<Hostel> getAllPermittedHostels();

    /**
     * 获得hostelId的客栈没有被结算的账单，
     *
     * @param hostelId
     * @return
     */
    public List<PayBill> getAllUncountedPayBills(int hostelId);

    /**
     * 下架房间
     *
     * @param roomId
     * @return
     */
    public ResultMessage invalidateRoom(int roomId);

    /**
     * 上架房间
     *
     * @param roomId
     * @return
     */
    public ResultMessage activeRoom(int roomId);

    /**
     * 所有入住人数统计
     *
     * @param hostelId
     * @return
     */
    public int getLiveInNum(int hostelId);

    /**
     * @param id
     * @return
     */
    public Hostel getHostel(int id);
}
