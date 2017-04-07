package edu.nju.hostelworld.service;

import edu.nju.hostelworld.entity.*;
import edu.nju.hostelworld.util.ResultMessage;
import vo.BookVO;

import java.util.List;

/**
 * Created by Administrator on 17/3/2.
 */
public interface MemberService {

    /**
     * 检查会员卡的激活时间和暂停卡时间
     *
     * @param memberId
     */
    public void init(int memberId);

    /**
     * 增/减会员卡余额
     *
     * @param memberId
     * @return NOT_ENOUGH_MONEY, SUCCESS, FAILURE
     */
    public ResultMessage payMoney(int memberId, double offset);

    /**
     * 会员充值，需要会员id和银行卡支付密码
     *
     * @param money
     * @param memberId
     * @param bankPassword
     * @return WRONG_PASSWORD, NOT_ENOUGH_MONEY(银行卡余额不足)
     * Member_STATE_STOP(停卡，不能充值)
     */
    public ResultMessage charge(double money, int memberId, String bankPassword);

    /**
     * 用户发出停卡请求
     *
     * @param memberId
     * @return
     */
    public ResultMessage stop(int memberId);

    /**
     * 根据会员ID获得会员数据：
     *
     * @param memberId
     * @return
     */
    public Member getById(int memberId);

    /**
     * 修改会员的基本信息
     *
     * @param member
     * @return
     */
    public ResultMessage update(Member member);

    /**
     * 会员预订房间
     *
     * @param bookVO
     * @return
     */
    public ResultMessage book(BookVO bookVO);

    /**
     * 会员取消预订
     *
     * @param memberId
     * @param bookId
     * @return NO_AUTHORITY, LATE_TIME, SUCESS, FAILURE;
     */
    public ResultMessage unbook(int memberId, int bookId);

    /**
     * 获取本人预订数据，包括预订和取消预订
     *
     * @param memberId
     * @return
     */
    public List<BookBill> getAllBookBills(int memberId);

    /**
     * 获取本人消费数据
     *
     * @param memberId
     * @return
     */
    public List<PayBill> getAllPayBills(int memberId);

    /**
     * 获取本人住店数据，包含入店、离店信息
     *
     * @param memberId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int memberId);

    /**
     * 会员将指定的积分数换成会员卡余额
     *
     * @param memberId
     * @param score
     * @return
     */
    public ResultMessage scoreToMoney(int memberId, double score);

    /**
     * 返回所有通过总经理审批的客栈
     *
     * @return
     */
    public List<Hostel> getAllPermittedHostels();
}
