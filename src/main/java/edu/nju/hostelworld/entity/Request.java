package edu.nju.hostelworld.entity;

import edu.nju.hostelworld.util.RequestState;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/11.
 */
@Entity
@Table(name = "request", schema = "hostelworld", catalog = "")

public class Request {
    private int id;
    private Hostel hostel;
    private String state= RequestState.UNCHECK.toString();

    @Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }
    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    @Basic
    @Column(name = "state", nullable = false)
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}