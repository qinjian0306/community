package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生认证
 */
@Data
@ToString
public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;
	// 医院
	private String hospital;
	// 医生真实姓名
	private String realName;
	// 详情
	private String detials;

	// 状态 0未审核 1批准 2拒绝
	private int status;

	private Date createTime;

}
