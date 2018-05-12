package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private List<String> urls;

	public void setUrl(String url) {
		urls = new ArrayList<>();
		this.url = url;
		if(StringUtils.isNotBlank(url) && url.indexOf(",")!=-1){
			String [] arr = url.split(",");
			for (int i=0;i<arr.length;i++){
				url = arr[i];
				urls.add(url);
			}
		}else {
			urls.add(url);
		}
	}

}
