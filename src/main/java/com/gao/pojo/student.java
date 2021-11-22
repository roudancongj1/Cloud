package com.gao.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class student implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private Double total;
    private String img;

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", total=" + total +
                ", img='" + img + '\'' +
                '}';
    }

    public student(Integer id, String name, Integer age, Double total, String img) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.total = total;
        this.img = img;
    }

    public student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
