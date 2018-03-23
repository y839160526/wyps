package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.HousesDTO;
import org.wyjc.util.Pager;

public interface HousesService {
	public List<HousesDTO> list();
	public void insert(HousesDTO housesDTO);
	public void delete(HousesDTO housesDTO);
	public HousesDTO get(int id);
	public Pager listByPager(Pager pager);

}
