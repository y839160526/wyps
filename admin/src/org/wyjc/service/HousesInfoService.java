package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.HousesInfoDTO;
import org.wyjc.util.Pager;

public interface HousesInfoService {
	public List<HousesInfoDTO> list();
	public void insert(HousesInfoDTO housesInfoDTO);
	public void delete(HousesInfoDTO housesInfoDTO);
	public HousesInfoDTO get(int id);
	public Pager listByPager(Pager pager);

}
