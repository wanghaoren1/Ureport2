package com.ure.provide;


import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;
import com.ure.entity.UreportFileEntity;
import com.ure.service.UReportFileService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Component
// 该注解可以利用其 prefix属性值 + 类的属性名 在yml中配置属性值
@ConfigurationProperties(prefix = "ureport.mysql.provider")
public class MySQLProvider implements ReportProvider {
    private static final String NAME = "mysql-provider";

    // 特定前缀，ureport底层会调用 getPrefix 方法来获取报表操作的Provider类
    private String prefix = "mysql:";

    // 是否禁用
    private boolean disabled = false;

    @Autowired
    private UReportFileService uReportFileService;

    @Override
    public InputStream loadReport(String file) {
        UreportFileEntity ureportFileEntity = uReportFileService.getReportFileByName(getCorrectName(file));
        byte[] content = new byte[0];
        ByteArrayInputStream inputStream=null;
        try {
            content = ureportFileEntity.getContent().getBytes("UTF-8");
            inputStream = new ByteArrayInputStream(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    @Override
    public void deleteReport(String file) {
        uReportFileService.removeReportFileByName(getCorrectName(file));
    }

    @Override
    public List<ReportFile> getReportFiles() {
        List<UreportFileEntity> list = uReportFileService.listAllReportFile();
        List<ReportFile> reportList = new ArrayList<>();
        for (UreportFileEntity ureportFileEntity : list) {
            reportList.add(new ReportFile(ureportFileEntity.getName(), ureportFileEntity.getUpdateTime()));
        }
        return reportList;
    }

    @Override
    public void saveReport(String file, String content) {
        file = getCorrectName(file);
        UreportFileEntity ureportFileEntity = uReportFileService.getReportFileByName(file);
        Date currentDate = new Date();
        if(ureportFileEntity == null){
            ureportFileEntity = new UreportFileEntity();
            ureportFileEntity.setId(UUID.randomUUID().toString());
            ureportFileEntity.setName(file);
            ureportFileEntity.setContent(content);
            ureportFileEntity.setCreateTime(currentDate);
            ureportFileEntity.setUpdateTime(currentDate);
            uReportFileService.saveReportFile(ureportFileEntity);
        }else{
            ureportFileEntity.setContent(content);
            ureportFileEntity.setUpdateTime(currentDate);
            uReportFileService.updateReportFile(ureportFileEntity);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public boolean disabled() {
        return disabled;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * 获取没有前缀的文件名
     * @param name
     * @return
     */
    private String getCorrectName(String name){
        if(name.startsWith(prefix)){
            name = name.substring(prefix.length(), name.length());
        }
        return name;
    }

}
