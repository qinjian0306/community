package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String username;
	// 昵称
	private String nickname;

	private String password;
	// 角色 分病人 医生 （管理员）。（不提供注册管理员功能）
	private int role;

	private String email;

	private String mobile;

}
