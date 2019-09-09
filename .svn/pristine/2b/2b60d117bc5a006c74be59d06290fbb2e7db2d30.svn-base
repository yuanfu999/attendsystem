package com.aboluo.attend.util;

import com.aboluo.attend.pojo.Attendance;
import com.aboluo.attend.pojo.Emp;

import java.util.ArrayList;
import java.util.List;

public class AttendPage {

    private int total;
    private int page_total;
    private List<Attendance> atds;

    private List<Attendance> all_atds;
    private int now_page;
    private int page_size = 7;
    private int end_page_size;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage_total() {
        return (total % page_size == 0 ? total / page_size : (total / page_size + 1));
    }


    public List<Attendance> getAtds() {
        return atds;
    }

    public void setAtds(List<Attendance> atds) {
        this.atds = atds;
    }

    public List<Attendance> getAll_atds() {
        return all_atds;
    }

    public void setAll_atds(List<Attendance> all_atds) {
        this.all_atds = all_atds;
    }

    public int getNow_page() {
        return now_page;
    }

    public void setNow_page(int now_page) {
        this.now_page = now_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getEnd_page_size() {
        if (getNow_page() == getPage_total() && total % page_size ==0){
            return getPage_size();
        }
        return total % page_size;
    }

    public void setEnd_page_size(int end_page_size) {
        this.end_page_size = end_page_size;
    }

    public List<Attendance> atd_page(){
        List<Attendance> atd_page = new ArrayList<>();
        if (now_page != this.getPage_total()){
            for (int i=(this.now_page-1)*page_size; i<this.now_page*page_size; i++){
                atd_page.add(all_atds.get(i));
            }
        }else{

            if (getPage_total() > 1){
                for (int i=(this.now_page-1)*page_size; i<(this.now_page-1)*page_size + this.getEnd_page_size(); i++){
                    atd_page.add(all_atds.get(i));

                }
            }else{
                for (int i=0; i<all_atds.size(); i++){
                    atd_page.add(all_atds.get(i));
                }
            }
        }
        return atd_page;
    }


}
