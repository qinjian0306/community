package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 医生VO类
 */
@Data
@ToString
public class UserVo extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String username;
	// 昵称
	private String nickname;

	private String password;
	// 角色 0分病人 1医生 （管理员）。（不提供注册管理员功能）
	private int role;

	private String email;

	private String mobile;

	// 医生状态 0未添加认证信息 1批准 2拒绝 3已添加认证信息 正在处理审核
	private Integer dstatus;

	/**医生认证信息**/
	private Integer doctorId;
	private String hospital;
	private String realName;
	private String detials;
}
