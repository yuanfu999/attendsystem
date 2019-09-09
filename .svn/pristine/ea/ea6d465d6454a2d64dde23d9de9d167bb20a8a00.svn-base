package com.aboluo.attend.service;

import com.aboluo.attend.pojo.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceService {

    List<Attendance> getAttendByPage(Map<String, Integer> index_map);

    int getAllAtdsCount();

    int insertAttendance(Attendance attendance);

    Attendance selectAttendById(Integer emp_id);

    int updateAttendance(Attendance attendance);

    int deleteAttendance(Integer emp_id);

    List<Attendance> searchByCondition(String dept);

    int searchByConditionCount(String dept);

    //模糊查询考勤记录
    List<Attendance> searchByContent(String content);

    //模糊查询考勤记录总条数
    int getSearchCount(String content);

    int deleteAllAttend();

    List<Attendance> getAllAttendance();

}
