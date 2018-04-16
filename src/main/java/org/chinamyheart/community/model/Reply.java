package org.chinamyheart.community.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 病历里面的留言
 */
@Data
@ToString
public class Reply extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private int userId;
	// 病历id
	private int caseId;
	// 留言内容
	private String content;

}
