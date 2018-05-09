package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 基类
 * @Author: qj
 */
@Data
@ToString
public class Base {

    private Date createTime;

    private Date updateTime;

    /** 创建时间－页面字段 */
    private Timestamp createTimeStr;
    /** 修改时间－页面字段 */
    private Timestamp updateTimeStr;


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        if (null != createTime) {
            createTimeStr = new Timestamp(createTime.getTime());
        }
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        if (null != updateTime) {
            createTime.getTime();
            updateTimeStr = new Timestamp(updateTime.getTime());
        }
    }

}
