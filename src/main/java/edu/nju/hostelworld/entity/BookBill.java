package edu.nju.hostelworld.entity;

import edu.nju.hostelworld.util.DateHandler;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/10.
 */
@Entity
@Table(name = "bookbill", schema = "hostelworld", catalog = "")
public class BookBill {
    private int id;
    private long checkInDate;
    private long checkOutDate;
    private long createDate;
    private Hostel hostel;
    private Member member;
    private HostelRoom room;
    private boolean valid = true;//如果类型为true，valid为false 就代表该预订已被取消

    @Id
    @GenericGenerator(name = "Administrator", strategy = "increment")
    @GeneratedValue(generator = "Administrator")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "valid", nullable = false)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    @Basic
    @Column(name = "checkInDate", nullable = false)
    public long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(long liveInDate) {
        this.checkInDate = liveInDate;
    }

    @Basic
    @Column(name = "checkOutDate", nullable = false)
    public long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(long liveInDate) {
        this.checkOutDate = liveInDate;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
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
    public String getCreateDateStr() {
        return DateHandler.longToStr(this.createDate);
    }

    @Transient
    public String getCheckInDateStr() {
        return DateHandler.longToStr(this.checkInDate);
    }

    @Transient
    public String getCheckOutDateStr() {
        return DateHandler.longToStr(this.checkOutDate);
    }

    @Transient
    public int getMemberId() {
        return this.member.getId();
    }
}
