package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.HousesDTO;
import org.wyjc.util.Pager;

public interface HousesDao {
public List<HousesDTO> list();
public void insert(HousesDTO housesDTO);
public void delete(HousesDTO housesDTO);
public HousesDTO get(int id);
public int getTotalCount();
public List<HousesDTO> listByPager(Pager pager);


}
