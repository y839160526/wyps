package org.wyjc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CityDTO;
import org.wyjc.entity.ProvinceDTO;
import org.wyjc.service.AreaService;
import org.wyjc.util.JsonUtil;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户管理
 * 
 * @author chenying
 * 
 */
public class AreaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Resource
	private AreaService areaService;
	private List<ProvinceDTO> provinceList;
	private List<CityDTO> cityList;
	private List<AreaDTO> areaList;
	private String resultStr;
	private String provinceId;
	private String cityId;

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public List<ProvinceDTO> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceDTO> provinceList) {
		this.provinceList = provinceList;
	}

	public List<CityDTO> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList) {
		this.cityList = cityList;
	}

	public List<AreaDTO> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<AreaDTO> areaList) {
		this.areaList = areaList;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProvinces() {
		provinceList = areaService.getProvinces();
		Map map = new HashMap();
		map.put("provinceList", provinceList);
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public String getCityesByProvinceId() {
		cityList = areaService.getCityesByProvinceId(provinceId);
		Map map = new HashMap();
		map.put("cityList", cityList);
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

	public String getAreasByCityId() {
		areaList = areaService.getAreasByCityId(cityId);
		Map map = new HashMap();
		map.put("areaList", areaList);
		this.resultStr = JsonUtil.returnDefaultAjax(map);
		return "json";
	}

}
