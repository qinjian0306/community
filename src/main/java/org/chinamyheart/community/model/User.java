package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User extends Base implements Serializable {

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

	// 状态 0未审核 1批准 2拒绝
	private Integer dstatus;
}
