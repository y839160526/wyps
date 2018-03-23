package org.wyjc.gap;

public class NextPage {
	private boolean hasNextConf=false;
	private String nextStep;
	private String nextPageType;
	public boolean isHasNextConf() {
		return hasNextConf;
	}
	public void setHasNextConf(boolean hasNextConf) {
		this.hasNextConf = hasNextConf;
	}
	
	public String getNextStep() {
		return nextStep;
	}
	public void setNextStep(String nextStep) {
		this.nextStep = nextStep;
	}
	public String getNextPageType() {
		return nextPageType;
	}
	public void setNextPageType(String nextPageType) {
		this.nextPageType = nextPageType;
	}

}
