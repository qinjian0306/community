package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 病历
 */
@Data
@ToString
public class Case extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;
	// 标题
	private String title;
	// 发布人（取user里的nickname）
	private String author;
	// 患者（有可能是一个人注册了号，但提交亲戚朋友的病历）
	private String patient;
	// 性别
	private int gender;
	// 联系方式
	private String contact;
	// 详情（描述病情）
	private String description;

	private String url;

	private int status;


	/** 拓展字段 **/
	private String nickname;
	private Integer dstatus;
}
