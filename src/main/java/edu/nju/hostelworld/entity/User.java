package edu.nju.hostelworld.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/10.
 */

@Entity
@Table(name = "user", schema = "hostelworld", catalog = "")
public class User {
    private int userid;//对应Member、Manager或者Hostel的
    private int id;
    private String userName;
    private String password = "root";
    private String type = "member";

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    private String bankId;//AutoSet = 111111111111 + id
    private String bankPassword = "bankroot";
    private double bankMoney = 3000;

    @Id
    @GenericGenerator(name = "Administrator", strategy = "increment")
    @GeneratedValue(generator = "Administrator")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        String bankId = "111111111111" + id;
        setBankId(bankId);
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "bankId", nullable = false, length = 255)
    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @Basic
    @Column(name = "bankPassword", nullable = false, length = 255)
    public String getBankPassword() {
        return bankPassword;
    }

    public void setBankPassword(String bankPassword) {
        this.bankPassword = bankPassword;
    }

    @Basic
    @Column(name = "bankMoney", nullable = false, length = 255)
    public double getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(double bankMoney) {
        this.bankMoney = bankMoney;
    }
}
