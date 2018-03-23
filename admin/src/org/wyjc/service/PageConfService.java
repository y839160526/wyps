package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.PageConf;


public interface PageConfService {
	public PageConf getPageConfById(String id);
	public long insertPageConf(PageConf conf);
	public void updatePageConfById(PageConf conf);
	public List<PageConf> getPageConfListByUserId(Long userId);
}
