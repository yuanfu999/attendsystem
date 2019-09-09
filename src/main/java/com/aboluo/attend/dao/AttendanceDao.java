package com.aboluo.attend.dao;

import com.aboluo.attend.pojo.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceDao {

    //分页获取数据（第一页）
    List<Attendance> getAttendByPage(Map<String, Integer> index_map);

    //获取总数
    int getAllAtdsCount();

    //插入考勤记录
    int insertAttendance(Attendance attendance);

    //通过id获取考勤记录
    Attendance selectAttendById(Integer emp_id);

    //更新考勤记录
    int updateAttendance(Attendance attendance);

    //删除考勤记录
    int deleteAttendance(Integer emp_id);

    //条件查询
    List<Attendance> searchByCondition(@Param("dept") String dept);

    //条件查询到的总记录数
    int searchByConditionCount(@Param("dept") String dept);

    //模糊查询考勤记录
    List<Attendance> searchByContent(@Param("content") String content);

    //模糊查询考勤记录总条数
    int getSearchCount(@Param("content") String content);

    //清除表中的所有数据
    int deleteAllAttend();

    //获取所有的考勤记录
    List<Attendance> getAllAttendance();

}
