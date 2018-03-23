package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.PageConfDao;
import org.wyjc.entity.PageConf;
import org.wyjc.service.PageConfService;

@Service
public class PageConfServiceImpl implements PageConfService {
	@Resource
	private PageConfDao pageConfDao;

	@Override
	public PageConf getPageConfById(String id) {
		return pageConfDao.getPageConfById(id);	
	}

	@Override
	public long insertPageConf(PageConf conf) {
		return pageConfDao.insertPageConf(conf);
	}

	@Override
	public void updatePageConfById(PageConf conf) {
		pageConfDao.updatePageConfById(conf);
		
	}

	@Override
	public List<PageConf> getPageConfListByUserId(Long userId) {
		return pageConfDao.getPageConfListByUserId(userId);
	}

	
	
}
