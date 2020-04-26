package com.ure;

import com.ure.entity.UreportFileEntity;
import com.ure.service.UReportFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author eRnest
 * @version 1.0
 * @date 2020/4/23 9:21
 */
@SpringBootTest
public class Test {
    @Autowired
    UReportFileService uReportFileService;
    @org.junit.Test
    public   void contextLoads() {
        List<UreportFileEntity> list =  uReportFileService.listAllReportFile();
        System.err.println(list.size());
    }
}
