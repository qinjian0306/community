package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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

	private int status;

}
