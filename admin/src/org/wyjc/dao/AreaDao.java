package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CityDTO;
import org.wyjc.entity.ProvinceDTO;

public interface AreaDao {
public List<ProvinceDTO> getProvinces();
public List<CityDTO> getCityesByProvinceId(String provinceId);
public List<AreaDTO> getAreasByCityId(String cityId);

}
