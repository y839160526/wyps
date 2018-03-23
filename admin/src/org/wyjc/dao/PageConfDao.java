package org.wyjc.dao;

import java.util.List;

import org.wyjc.entity.PageConf;

public interface PageConfDao {

public PageConf getPageConfById(String id);
public long insertPageConf(PageConf conf);
public void updatePageConfById(PageConf conf);
public List<PageConf> getPageConfListByUserId(Long userId);

}
