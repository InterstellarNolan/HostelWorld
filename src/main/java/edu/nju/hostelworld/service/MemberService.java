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
     * 如果到期要改变状态
     * 激活一年后，会员卡余额不足则该会员卡状态改为暂停，能查记录，但不能预订、结账
     * 暂停一年后，会员一直未支付到规定余额，则该会员卡状态改为停止，能查记录，但不能预订、结账，且不可恢复
     *
     * @param memberId
     */
    public void init(int memberId);

    /**
     * 增/减会员卡余额
     * offset为+是从会员卡里扣钱
     * 包括  用户预订扣钱，取消预订加钱，结账扣钱
     * 设置为public是为了 比如客栈结账的时候，会员选择【会员卡支付】，
     * 客栈要负责在会员的卡扣钱
     *
     * @param memberId
     * @return NOT_ENOUGH_MONEY, SUCCESS, FAILURE
     */
    public ResultMessage payMoney(int memberId, double offset);

    /**
     * 会员充值，需要会员id和银行卡支付密码
     * 从银行卡扣钱，会员卡加钱
     * 在这个方法里判断会员卡是否为【未激活】、【暂停】、【停止】（停止则不能交费）
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
     * 能查记录，但不能预订、结账，且不可恢复
     *
     * @param memberId
     * @return
     */
    public ResultMessage stop(int memberId);

    /**
     * 根据会员ID获得会员数据：
     * 姓名、身份证号、头像、会员卡余额、消费总金额、会员等级、积分
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
     * 会员预订房间（可能会余额不足），
     * 考虑会员级别不同，预订费变化
     *
     * @param bookVO
     * @return
     */
    public ResultMessage book(BookVO bookVO);

    /**
     * 会员取消预订。bookId是预定订单id，会在预订订单生成时自动生成
     * 【前置】会员有未结账的预订记录
     *
     * @param memberId
     * @param bookId
     * @return NO_AUTHORITY, LATE_TIME, SUCESS, FAILURE;
     */
    public ResultMessage unbook(int memberId, int bookId);

    /**
     * 获取本人预订数据，包括预订和取消预订
     * 比如 2016-12-02 预订 标间 入住时间2016-12-10  2晚 358元+图片
     * 2016-12-02 取消预订 标间 入住时间2016-12-10   2晚 358元+图片
     * 2016-12-02 预订 豪华总统套房 入住时间2016-12-10   2晚 2288元+图片
     *
     * @param memberId
     * @return
     */
    public List<BookBill> getAllBookBills(int memberId);

    /**
     * 获取本人消费数据
     * 比如
     * 2016-12-02 预订房间 标间[含房间详情]-20元
     * 2016-12-02 取消预订 标间[含房间详情]+20元
     * 2016-12-02 预订房间 豪华总统套房[含房间详情]-20元
     * 2016-12-10 消费 豪华总统套房[含房间详情]-2288元
     *
     * @param memberId
     * @return
     */
    public List<PayBill> getAllPayBills(int memberId);

    /**
     * 获取本人住店数据，包含入店、离店信息
     * 比如
     * 2016-12-10 入店 豪华总统套房
     * 2016-12-10 离店 豪华总统套房
     *
     * @param memberId
     * @return
     */
    public List<LiveBill> getAllLiveBills(int memberId);

    /**
     * 会员将指定的积分数换成会员卡余额
     * 要注意用户只能填小于自己积分总值的积分数
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
