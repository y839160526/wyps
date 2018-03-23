package org.wyjc.entity;

public class Pager {
	private int pagenum=1;
	private int currentnum=1;
	private int pagesize=10;
	private long confid;
	private long totalnum;
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public long getConfid() {
		return confid;
	}
	public void setConfid(long confid) {
		this.confid = confid;
	}
	public long getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(long totalnum) {
		this.totalnum = totalnum;
	}
	public int getCurrentnum() {
		return currentnum;
	}
	public void setCurrentnum(int currentnum) {
		this.currentnum = currentnum;
	}

}
