package org.wyjc.entity;

//售房租房信息类
public class HousesDTO {
	private int id;
	private String userInfoId;
	private String buildingInfoId;
	private String housesInfoId;
	private String houseType;// 1售房 2租房

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getBuildingInfoId() {
		return buildingInfoId;
	}

	public void setBuildingInfoId(String buildingInfoId) {
		this.buildingInfoId = buildingInfoId;
	}

	public String getHousesInfoId() {
		return housesInfoId;
	}

	public void setHousesInfoId(String housesInfoId) {
		this.housesInfoId = housesInfoId;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

}
