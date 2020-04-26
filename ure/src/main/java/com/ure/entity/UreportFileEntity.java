package com.ure.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UreportFileEntity {

    /** 主键 */
    private String id;
    /** 报表名称 */
    private String name;
    /** 报表内容 */
    private String content;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;


}