package org.wyjc.gap;

public class CssSelector {
	private int size=0;
	private String text="";
	private String selector;
	private String href;
	private boolean isAdd=false;
	private int index=-1;
	private int wordAvgNum;
	private String regex;
	
	
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public boolean isAdd() {
		return isAdd;
	}
	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}
	public int getWordAvgNum() {
		return wordAvgNum;
	}
	public void setWordAvgNum(int wordAvgNum) {
		this.wordAvgNum = wordAvgNum;
	}
	

}
