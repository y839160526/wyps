package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CityDTO;
import org.wyjc.entity.ProvinceDTO;


public interface AreaService {
	public List<ProvinceDTO> getProvinces();
	public List<CityDTO> getCityesByProvinceId(String provinceId);
	public List<AreaDTO> getAreasByCityId(String cityId);
}
