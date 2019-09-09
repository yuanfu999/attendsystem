package com.aboluo.attend.controller;

import com.aboluo.attend.pojo.Attendance;
import com.aboluo.attend.service.AttendanceService;
import com.aboluo.attend.util.AttendPage;
import com.aboluo.attend.util.ExcelUtil;
import com.aboluo.attend.util.Page;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/attend")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/atdList")
    public String getAttendanceDefaultPage(Model model){
        int start_index = 1;
        int page_size = 7;
        Map<String, Integer> index_map = new HashMap<>();
        index_map.put("start_index", start_index-1);
        index_map.put("page_size", page_size);
        List<Attendance> atds = attendanceService.getAttendByPage(index_map);
        int total = attendanceService.getAllAtdsCount();
        AttendPage page = new AttendPage();
        page.setAtds(atds);
        page.setPage_size(7);
        page.setNow_page(start_index);
        page.setTotal(total);
        model.addAttribute("page", page);
        return "admin/attendance";
    }

    @RequestMapping(value = "/page/{index}", method = RequestMethod.GET)
    @ResponseBody
    public AttendPage getAttendanceByPage(@PathVariable("index") Integer index){
        AttendPage page = new AttendPage();
        int page_size = 7;
        int start_index = (index-1)*page_size;
        Map<String, Integer> index_map = new HashMap<>();
        index_map.put("start_index", start_index);
        index_map.put("page_size", page_size);
        List<Attendance> atds = attendanceService.getAttendByPage(index_map);
        int total = attendanceService.getAllAtdsCount();
        page.setTotal(total);
        page.setNow_page(index);
        page.setPage_size(page_size);
        page.setAtds(atds);
        return page;
    }

    /*
    * 添加考勤记录
    * */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createAdttend(Attendance attendance){
        int count = attendanceService.insertAttendance(attendance);
        if (count > 0){
            return "ok";
        }else{
            return "fail";
        }
    }

    /*
    * 通过id获取考勤记录
    * */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    @ResponseBody
    public Attendance selectAttendById(Integer emp_id){
        Attendance attendance = attendanceService.selectAttendById(emp_id);
        if (attendance != null){
            return attendance;
        }else{
            return null;
        }
    }

    /*
    * 修改考勤记录
    * */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateAttend(Attendance attendance){
        int count = attendanceService.updateAttendance(attendance);
        if (count > 0){
            return "ok";
        }else{
            return "fail";
        }
    }

    /*
    * 删除考勤记录
    * */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAttend(Integer emp_id){
        int count = attendanceService.deleteAttendance(emp_id);
        if (count > 0){
            return "ok";
        }else {
            return "fail";
        }
    }

    /*
    * 条件查询
    * dept:所属部门
    * */
    @RequestMapping(value = "/tj_srch", method = RequestMethod.GET)
    @ResponseBody
    public AttendPage searchByCondition(@RequestParam("dept") String dept,
                                        @RequestParam(value = "start_index", defaultValue = "1") Integer index) throws UnsupportedEncodingException {
        AttendPage page = new AttendPage();
        List<Attendance> atds = attendanceService.searchByCondition(dept);
        int total = attendanceService.searchByConditionCount(dept);
        page.setTotal(total);
        page.setNow_page(index);
        page.setAll_atds(atds);
        page.setPage_size(7);
        List<Attendance> page_atds = page.atd_page();
        page.setAtds(page_atds);
        return page;
    }


    /*
    * 模糊查询
    * */
    @RequestMapping(value = "/mh_srch", method = RequestMethod.GET)
    @ResponseBody
    public AttendPage searchByContent(@RequestParam("srch_content") String content,
                                      @RequestParam(value = "start_index", defaultValue = "1") Integer index,
                                      HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        AttendPage page = new AttendPage();
        List<Attendance> all_atds = attendanceService.searchByContent(content);
        int total = attendanceService.getSearchCount(content);
        page.setTotal(total);
        page.setAll_atds(all_atds);
        page.setNow_page(index);
        page.setPage_size(7);
        List<Attendance> atds_page = page.atd_page();
        page.setAtds(atds_page);
        return page;
    }

    /*
    * 导入考勤记录
    * */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String importAttendInfo(HttpServletRequest request, @RequestParam(value = "import_file") MultipartFile partFile){
        if (!partFile.isEmpty()){
            try {
                String path = request.getServletContext().getRealPath("/upload");
                //定义文件
                File parent = new File(path);
                if(!parent.exists()) parent.mkdirs();


                System.out.println(path);

                String filename = partFile.getOriginalFilename();
                File file = new File(path+"/"+filename);
                String filePath = path+"/"+filename;
                InputStream inputStream = partFile.getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, file);
                if(inputStream!=null){
                    inputStream.close();
                }
                ExcelUtil excel = new ExcelUtil();
                List<Attendance> atds = excel.Excel2Mysql(filePath, "考勤汇总", 4, 0);
                int atd_count = attendanceService.getAllAtdsCount();
                int count = attendanceService.deleteAllAttend();
                if (count > 0 || atd_count == 0){
                    for (Attendance attendance : atds){
                        attendanceService.insertAttendance(attendance);
                    }
                }
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return "fail";
            }
        }else{
            return "fail";
        }
    }

    /*
    * 导出数据，下载文件
    * */
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void outputInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Attendance> atds = attendanceService.getAllAttendance();
        ExcelUtil excelUtil = new ExcelUtil();
        String[] headers = {"员工编号", "所属部门", "员工姓名", "迟到次数", "早退次数", "出差次数", "旷工次数", "请假次数"};
        String filename = "考勤记录表.xls";
        // 文件名称
        String fileName = URLEncoder.encode(filename, "utf-8");
        // 通过response设置Content-Type、Content-Disposition
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
                "attachment;filename*=utf-8'zh_cn'" + fileName);

        OutputStream outputStream = null;
        HSSFWorkbook workBook = null;

        try {
            // 获取输出流
            outputStream = response.getOutputStream();
            // 生成workBook
            workBook = excelUtil.createWorkbook(atds, headers);
            workBook.write(outputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭
            if (outputStream!=null) {
                outputStream.close();
            }
        }
    }

}
