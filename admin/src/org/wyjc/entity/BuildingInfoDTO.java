package org.wyjc.entity;
//楼盘表
public class BuildingInfoDTO {
	private int id;
	private String buildingName;
	private String province;
	private String city;
	private String area;
	private String address;
	private String use;
	private String buildingType;
	private String structure;
	private String developer;
	private float costs;
	private String costsUnit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public float getCosts() {
		return costs;
	}
	public void setCosts(float costs) {
		this.costs = costs;
	}
	public String getCostsUnit() {
		return costsUnit;
	}
	public void setCostsUnit(String costsUnit) {
		this.costsUnit = costsUnit;
	}

}
