package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.BuildingInfoDTO;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.util.Pager;

public interface BuildingInfoDao {
public List<BuildingInfoDTO> list();
public void insert(BuildingInfoDTO buildingInfoDTO);
public void delete(BuildingInfoDTO buildingInfoDTO);
public BuildingInfoDTO get(int id);
public int getTotalCount();
public List<BuildingInfoDTO> listByPager(Pager pager);


}
