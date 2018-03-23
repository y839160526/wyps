package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.BuildingInfoDTO;
import org.wyjc.util.Pager;

public interface BuildingInfoService {
	public List<BuildingInfoDTO> list();
	public void insert(BuildingInfoDTO buildingInfoDTO);
	public void delete(BuildingInfoDTO buildingInfoDTO);
	public BuildingInfoDTO get(int id);
	public Pager listByPager(Pager pager);

}
