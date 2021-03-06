package edu.nju.hostelworld.entity;

import edu.nju.hostelworld.util.MemberState;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 * 会员
 */
@Entity
@Table(name = "member", schema = "hostelworldnew", catalog = "")
public class Member {
    private int id;
    private int userid = 1000000;
    private String realName = "匿名";
    private String idCard = "111111197001011111";
    private String avatar;
    private double moneyLeft = 0;
    private double moneyPaid = 0;
    private int level = 0;
    private double score = 0;
    private String state = MemberState.UNACTIVATED.toString();
    private long activateDate;
    private long pauseDate;
    private List<BookBill> bookBills;
    private List<LiveBill> liveBills;
    private List<PayBill> payBills;

    @Id
    @GenericGenerator(name = "Administrator", strategy = "increment")
    @GeneratedValue(generator = "Administrator")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.userid = id + userid;
    }


    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "realName", nullable = false, length = 255)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "activateDate", nullable = false)
    public long getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(long activateDate) {
        this.activateDate = activateDate;
    }

    @Basic
    @Column(name = "pauseDate", nullable = false)
    public long getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(long pauseDate) {
        this.pauseDate = pauseDate;
    }

    @Basic
    @Column(name = "idCard", nullable = true, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "moneyLeft", nullable = false, precision = 0)
    public double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    @Basic
    @Column(name = "moneyPaid", nullable = false, precision = 0)
    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "score", nullable = false, precision = 0)
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 255)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "member")
    public List<BookBill> getBookBills() {
        return bookBills;
    }

    public void setBookBills(List<BookBill> bookBills) {
        this.bookBills = bookBills;
    }

    @OneToMany(mappedBy = "member")
    public List<LiveBill> getLiveBills() {
        return liveBills;
    }

    public void setLiveBills(List<LiveBill> liveBillsById) {
        this.liveBills = liveBillsById;
    }

    @OneToMany(mappedBy = "member")
    public List<PayBill> getPayBills() {
        return payBills;
    }

    public void setPayBills(List<PayBill> payBills) {
        this.payBills = payBills;
    }
}
