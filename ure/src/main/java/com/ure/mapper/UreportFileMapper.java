package com.ure.mapper;



import com.ure.entity.UreportFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UreportFileMapper {
    /**
     *  根据报表名称检查报表是否存在
     *
     * @param name 报表名称
     */
    int checkExistByName(@Param("name") String name);

    /**
     *  根据报表名称查询报表
     *
     * @param name 报表名称
     */
    UreportFileEntity getReportFileByName(@Param("name") String name);

    /**
     * 查询全部报表
     */
    List<UreportFileEntity> listAllReportFile();

    List<UreportFileEntity> selectUreportfileList(UreportFileEntity entity);

    /**
     * 根据报表名称删除报表
     *
     * @param name 报表名称
     */
    int removeReportFileByName(@Param("name") String name);


    /**
     *  保存报表
     */
    int saveReportFile(UreportFileEntity entity);

    /**
     *  更新报表
     */
    int updateReportFile(UreportFileEntity entity);

}
