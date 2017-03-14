package edu.nju.hostelworld.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 * 客栈
 */
@Entity
@Table(name = "hostel", schema = "hostelworld", catalog = "")
public class Hostel {
    private int id;
    private int userid;
    private boolean permitted = false;
    private String img;
    private String phone = "66668888";
    private String address = "南京";
    private String name;
    private double moneyUncounted;
    private List<BookBill> bookBills;
    private List<LiveBill> liveBills;
    private List<PayBill> payBills;
    private List<HostelRoom> rooms;

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
    @Column(name = "userid", nullable = false, length = 255)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    @Basic
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "permitted", nullable = false)
    public boolean getPermitted() {
        return permitted;
    }

    public void setPermitted(boolean permitted) {
        this.permitted = permitted;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "moneyuncounted", nullable = false)
    public double getMoneyUncounted() {
        return moneyUncounted;
    }

    public void setMoneyUncounted(double name) {
        this.moneyUncounted = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hostel that = (Hostel) o;

        if (id != that.id) return false;
        if (permitted != that.permitted) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }


    @OneToMany(mappedBy = "hostel")
    public List<BookBill> getBookBills() {
        return bookBills;
    }

    public void setBookBills(List<BookBill> bookBills) {
        this.bookBills = bookBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<LiveBill> getLiveBills() {
        return liveBills;
    }

    public void setLiveBills(List<LiveBill> liveBills) {
        this.liveBills = liveBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<PayBill> getPayBills() {
        return payBills;
    }

    public void setPayBills(List<PayBill> payBills) {
        this.payBills = payBills;
    }

    @OneToMany(mappedBy = "hostel")
    public List<HostelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HostelRoom> rooms) {
        this.rooms = rooms;
    }
}
