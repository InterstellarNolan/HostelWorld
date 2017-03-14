package edu.nju.hostelworld.entity;

import edu.nju.hostelworld.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/11.
 */
@Entity
@Table(name = "livebill", schema = "hostelworld", catalog = "")
public class LiveBill {
    private int id;
    //true代表入店，false代表离店
    private boolean type;
    private String userRealName;
    private String idCard;
    private long date;
    private Hostel hostel;
    private Member member;
    private HostelRoom room;

    @Id
    @GenericGenerator(name="Administrator" , strategy="increment")
    @GeneratedValue(generator="Administrator")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Basic
    @Column(name = "userRealName", nullable = false)
    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    @Basic
    @Column(name = "idCard", nullable = false, length = 255)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    @ManyToOne
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id", nullable = false)
    public HostelRoom getRoom() {
        return room;
    }

    public void setRoom(HostelRoom room) {
        this.room = room;
    }

    @Transient
    public String getDateStr(){
        return DateHandler.longToStr(this.date);
    }
}
