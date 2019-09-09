package com.aboluo.attend.pojo;

import java.io.Serializable;

public class Emp implements Serializable {
    private int emp_id;
    private String dept;
    private String emp_name;
    private String stu_id;
    private String gender;
    private String tel;
    private String address;
    private int disable;
    private int level;
    private String password;

    public Emp(){}

    public Emp(String emp_name,String password,int level){
        this.emp_name = emp_name;
        this.password = password;
        this.level = level;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "emp_id=" + emp_id +
                ", dept='" + dept + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", gender='" + gender + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", disable=" + disable +
                ", level=" + level +
                ", password='" + password + '\'' +
                '}';
    }
}
