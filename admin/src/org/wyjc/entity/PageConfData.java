package org.wyjc.entity;

import java.util.Date;

public class PageConfData {
	private long id;
	private long pageConfId;
	private String data;
	private Date updatetime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPageConfId() {
		return pageConfId;
	}
	public void setPageConfId(long pageConfId) {
		this.pageConfId = pageConfId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
