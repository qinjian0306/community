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
public class Doctor extends Base implements Serializable {

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDetials() {
		return detials;
	}

	public void setDetials(String detials) {
		this.detials = detials;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
