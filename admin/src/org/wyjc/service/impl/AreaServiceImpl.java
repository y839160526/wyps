package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.AreaDao;
import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CityDTO;
import org.wyjc.entity.ProvinceDTO;
import org.wyjc.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Resource
	private AreaDao areaDao;

	@Override
	public List<ProvinceDTO> getProvinces() {
		return this.areaDao.getProvinces();
	}

	@Override
	public List<CityDTO> getCityesByProvinceId(String provinceId) {
		return this.areaDao.getCityesByProvinceId(provinceId);
	}

	@Override
	public List<AreaDTO> getAreasByCityId(String cityId) {
		return this.areaDao.getAreasByCityId(cityId);
	}

}
