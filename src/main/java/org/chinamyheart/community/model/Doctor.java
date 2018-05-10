package org.chinamyheart.community.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生认证
 */
@Data
@ToString
public class Doctor extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;
	// 医院
	private String hospital;
	// 联系电话
	private String mobile;
	// 医生真实姓名
	private String realName;
	// 详情
	private String detials;

}
