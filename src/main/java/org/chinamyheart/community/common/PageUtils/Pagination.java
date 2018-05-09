package org.chinamyheart.community.common.PageUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 分页工具类
 * @author ZKF
 */
public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 总行数 */
	private int totalRows;

	/** 每页显示的行数 */
	private int pageSize;

	/** 当前页号 */
	private int currentPage;

	/** 总页数 */
	private int totalPages;

	/** 偏移数 */
	private int offset;

	private Collection<T> data;

	private Map<String, Object> param;

	public Pagination() {
	}

	public Pagination(int currentPage, int pageSize) {
		this.pageSize = pageSize;
		if(currentPage < 1){
			currentPage = 1;
		}
		this.currentPage = currentPage;
		// 偏移量
		this.offset = (this.currentPage - 1) * pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		if(pageSize<=0){
			return;
		}
		this.totalPages = ((totalRows - 1) / pageSize) + 1;
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage < 1){
			currentPage = 1;
		}
		this.currentPage = currentPage;
		// 偏移量
		this.offset = (this.currentPage - 1) * pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Collection<T> getData() {
		if(data == null){
			return new ArrayList<T>();
		}
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	public int getOffset() {
		return offset;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

}