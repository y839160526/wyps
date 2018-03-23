package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.PageConfDataDao;
import org.wyjc.entity.PageConf;
import org.wyjc.entity.PageConfData;
import org.wyjc.entity.Pager;
import org.wyjc.service.PageConfDataService;

@Service
public class PageConfDataServiceImpl implements PageConfDataService {
	@Resource
	private PageConfDataDao pageConfDataDao;

	@Override
	public List<PageConfData> getPageConfDataByConfId(String id) {
		return pageConfDataDao.getPageConfDataByConfId(id);	
	}

	@Override
	public void insertPageConfData(PageConfData data) {
		pageConfDataDao.insertPageConfData(data);
		
	}

	@Override
	public long getPageConfDataCountById(String confId) {
		return pageConfDataDao.getPageConfDataCountById(confId);
	}

	@Override
	public List<PageConfData> getPageConfDataByPager(Pager pager) {
		return pageConfDataDao.getPageConfDataByPager(pager);
	}

	@Override
	public void deleteConfData(String id) {
		pageConfDataDao.deleteConfData(id);
		
	}


	
	
}
