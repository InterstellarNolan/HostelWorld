package edu.nju.hostelworld.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/12.
 */
@Entity
@Table(name = "manager", schema = "hostelworld", catalog = "")
public class Manager {
    private int id = 9000000;
    private int userId;
    private String name;
    private String phone = "76668888";
    private String address = "北京";
    private String avatar;

    @Id
    @GenericGenerator(name = "Administrator", strategy = "increment")
    @GeneratedValue(generator = "Administrator")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id + id;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 255)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
