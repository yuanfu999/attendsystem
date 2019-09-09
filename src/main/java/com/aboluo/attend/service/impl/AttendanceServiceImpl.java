package com.aboluo.attend.service.impl;

import com.aboluo.attend.dao.AttendanceDao;
import com.aboluo.attend.pojo.Attendance;
import com.aboluo.attend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("attendanceService")
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Override
    public List<Attendance> getAttendByPage(Map<String, Integer> index_map) {
        return attendanceDao.getAttendByPage(index_map);
    }

    @Override
    public int getAllAtdsCount() {
        return attendanceDao.getAllAtdsCount();
    }

    @Override
    public int insertAttendance(Attendance attendance) {
        return attendanceDao.insertAttendance(attendance);
    }

    @Override
    public Attendance selectAttendById(Integer emp_id) {
        return attendanceDao.selectAttendById(emp_id);
    }

    @Override
    public int updateAttendance(Attendance attendance) {
        return attendanceDao.updateAttendance(attendance);
    }

    @Override
    public int deleteAttendance(Integer emp_id) {
        return attendanceDao.deleteAttendance(emp_id);
    }

    @Override
    public List<Attendance> searchByCondition(String dept) {
        return attendanceDao.searchByCondition(dept);
    }

    @Override
    public int searchByConditionCount(String dept) {
        return attendanceDao.searchByConditionCount(dept);
    }

    @Override
    public List<Attendance> searchByContent(String content) {
        return attendanceDao.searchByContent(content);
    }

    @Override
    public int getSearchCount(String content) {
        return attendanceDao.getSearchCount(content);
    }

    @Override
    public int deleteAllAttend() {
        return attendanceDao.deleteAllAttend();
    }

    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceDao.getAllAttendance();
    }


}
