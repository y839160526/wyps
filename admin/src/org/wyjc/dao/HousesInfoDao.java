package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.HousesInfoDTO;
import org.wyjc.util.Pager;

public interface HousesInfoDao {
public List<HousesInfoDTO> list();
public void insert(HousesInfoDTO housesInfoDTO);
public void delete(HousesInfoDTO housesInfoDTO);
public HousesInfoDTO get(int id);
public int getTotalCount();
public List<HousesInfoDTO> listByPager(Pager pager);


}
