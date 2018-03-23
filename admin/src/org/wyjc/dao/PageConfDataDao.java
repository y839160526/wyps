package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.PageConfData;
import org.wyjc.entity.Pager;

public interface PageConfDataDao {

public List<PageConfData> getPageConfDataByConfId(String confId);
public void insertPageConfData(PageConfData data);
public long getPageConfDataCountById(String confId);
public List<PageConfData> getPageConfDataByPager(Pager pager);
public void deleteConfData(String id);
}
