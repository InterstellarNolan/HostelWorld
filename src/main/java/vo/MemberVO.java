package vo;

import edu.nju.hostelworld.entity.LiveBill;
import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.util.MemberState;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class MemberVO {
    private int id;
    private String realName;
    private String idCard;
    private String avatar;
    private double moneyLeft;
    private double moneyPaid;
    private int level;
    private double score;
    private MemberState state;

    public MemberVO(int id, String realName, String idCard, String avatar, double moneyLeft, double moneyPaid, int level, double score, MemberState state) {
        this.id = id;
        this.realName = realName;
        this.idCard = idCard;
        this.avatar = avatar;
        this.moneyLeft = moneyLeft;
        this.moneyPaid = moneyPaid;
        this.level = level;
        this.score = score;
        this.state = state;
    }
    public MemberVO(Member vipEntity){
        this.id = vipEntity.getId();
        this.realName = vipEntity.getRealName();
        this.idCard = vipEntity.getIdCard();
        this.avatar = vipEntity.getAvatar();
        this.moneyLeft = vipEntity.getMoneyLeft();
        this.moneyPaid = vipEntity.getMoneyPaid();
        this.level = vipEntity.getLevel();
        this.score = vipEntity.getScore();
        this.state = MemberState.strToMemberState(vipEntity.getState());
    }

    public static List<MemberVO> entityToVO(List<LiveBill> bills){
        //TOOD
        return null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public MemberState getState() {
        return state;
    }

    public void setState(MemberState state) {
        this.state = state;
    }
}
