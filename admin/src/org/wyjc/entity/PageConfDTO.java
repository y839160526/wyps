package org.wyjc.entity;

import java.util.Date;

public class PageConfDTO {
	private long id;
	private long userId;
	private String targetUrl;
	private String listSelector;
	private String[] fieldsSelector;
	private String[] fieldsName;
	private String fenye;
	private String nextSelector;
	private String[] listName;
	private int waitTime=0;
	private Date createtime;
	private Date updatetime;
	
	public String getFenye() {
		return fenye;
	}
	public void setFenye(String fenye) {
		this.fenye = fenye;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getListSelector() {
		return listSelector;
	}
	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}
	public String[] getFieldsSelector() {
		return fieldsSelector;
	}
	public void setFieldsSelector(String[] fieldsSelector) {
		this.fieldsSelector = fieldsSelector;
	}
	public String[] getFieldsName() {
		return fieldsName;
	}
	public void setFieldsName(String[] fieldsName) {
		this.fieldsName = fieldsName;
	}
	public String getNextSelector() {
		return nextSelector;
	}
	public void setNextSelector(String nextSelector) {
		this.nextSelector = nextSelector;
	}
	public String[] getListName() {
		return listName;
	}
	public void setListName(String[] listName) {
		this.listName = listName;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}
