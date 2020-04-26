package com.ure.service;


import com.ure.entity.UreportFileEntity;
import com.ure.mapper.UreportFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UReportFileService {

    @Autowired
    private UreportFileMapper ureportFileMapper;


    public int checkExistByName(String name){
        return ureportFileMapper.checkExistByName(name);
    }

    public UreportFileEntity getReportFileByName(String name){
        return ureportFileMapper.getReportFileByName(name);
    }

    public List<UreportFileEntity> listAllReportFile(){
        return ureportFileMapper.listAllReportFile();
    }

    public List<UreportFileEntity> selectUreportfileList(UreportFileEntity entity){
        return ureportFileMapper.selectUreportfileList(entity);
    }

    public int removeReportFileByName(String name){
        return ureportFileMapper.removeReportFileByName(name);
    }

    public int saveReportFile(UreportFileEntity entity){
        return ureportFileMapper.saveReportFile(entity);
    }

    public int updateReportFile(UreportFileEntity entity){
        return ureportFileMapper.updateReportFile(entity);
    }
}
