package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

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

}
