package com.lilei.boot.Controller;


import Tool.Dao.PfflowinstanceDao;
import Tool.Json.Json;
import Tool.Pojo.Pfflowinstance;
import Tool.Pojo.ReceiveRecord;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

    private List<ReceiveRecord> getReceiveRecordList() throws IOException {
        List<JSONArray> results = new Json().getResults();
        List<ReceiveRecord> rrList = new ArrayList<ReceiveRecord>();

        results.forEach(e->{
            ReceiveRecord rr = new ReceiveRecord();
            rr.setDocname(e.getString(3));
            rr.setFaqi(e.getString(1));
            rr.setGwid(e.getString(0));
            rr.setJieshou(e.getString(2));
            rr.setJieshouzhunagtai(e.getString(5));
            rr.setLeixing(e.getString(4));
            rr.setShifouyijieshou(e.getString(6));
            rr.setJieshoushijian(e.getString(7));
            rrList.add(rr);
        });
        return rrList;
    }

    @ResponseBody
    @RequestMapping("/")
    public String handle01(){
        return "Hello,Spring Boot 2";
    }

    @RequestMapping("/ReceiveRecordCrudAll")
    public String handle02(Model model) throws IOException {
        List<ReceiveRecord> rrList = getReceiveRecordList();

        model.addAttribute("rrList",rrList);
        return "index";
    }

    @RequestMapping("/ReceiveRecordCrudGroup")
    public String handle03(Model model) throws IOException {
        List<ReceiveRecord> rrList = getReceiveRecordList();

        Set<Map.Entry<String, Long>> newRrList = rrList.stream()
                .collect(Collectors.groupingBy(e -> e.getJieshou(), Collectors.counting()))
                .entrySet();
        model.addAttribute("newRrList",newRrList);
        return "index2";
    }

    @GetMapping("/uploader")
    public String handle04(){
        return "uploader";
    }

    @ResponseBody
    @PostMapping("/uploader")
    public String handle05(@RequestParam("file") MultipartFile file, HttpServletRequest req) throws IOException {

        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return "failed";
        }
        // 获取文件存储路径（绝对路径）
        String path = "E:\\java\\IntelliJ IDEA\\JetBrains\\IntelliJ IDEA 2020.3.2\\projects\\mavenTest\\src\\main\\resources\\public";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件

        file.transferTo(filePath);

        return "success";
    }

    @GetMapping("/checkFile")
    public String handle06(String guanjianzi,Model model) throws IOException {

        String parameter = guanjianzi;

        parameter = (parameter=="")?"账号密码":parameter;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"oracle");
        SqlSession session = sqlSessionFactory.openSession();

        PfflowinstanceDao pfflowinstanceDao = session.getMapper(PfflowinstanceDao.class);
        List<Pfflowinstance> pfflowinstances = pfflowinstanceDao.getAll("%"+parameter+"%");


        List<String> results = new ArrayList<String>();
        pfflowinstances.stream()
                .limit(100)
                .forEach(e->results.add(e.getTitle()));
        session.close();

        results.forEach(System.out::println);
        model.addAttribute("content",results);
        return "checkFile";
    }

    @ResponseBody
    @PostMapping("/excel")
    public String handle07() throws IOException {
        String FileName = "E:\\java\\IntelliJ IDEA\\JetBrains\\IntelliJ IDEA 2020.3.2\\projects\\mavenTest\\src\\main\\resources\\public\\收文列表write.xls";
        List<ReceiveRecord> rrList = getReceiveRecordList();
        EasyExcel.write(FileName,ReceiveRecord.class).sheet().doWrite(rrList);
        return "生成excel成功";
    }
}
