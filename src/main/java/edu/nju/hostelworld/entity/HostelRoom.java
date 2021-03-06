package edu.nju.hostelworld.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/10.
 */
@Entity
@Table(name = "hostelroom", schema = "hostelworldnew", catalog = "")
public class HostelRoom {
    private int id;
    private double price = 299;
    private String img;
    private boolean valid = true;
    private String name;
    private Hostel hostel;

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
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    @Column(name = "valid", nullable = true)
    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HostelRoom that = (HostelRoom) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (valid != that.valid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @ManyToOne
    @JoinColumn(name = "hostelId", referencedColumnName = "id", nullable = false)
    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
