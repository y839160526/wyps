package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.AttributeDao;
import org.wyjc.dao.CustomerDao;
import org.wyjc.entity.AttributeDTO;
import org.wyjc.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {
	@Resource
	private AttributeDao attributeDao;

	
	public AttributeDao getAttributeDao() {
		return attributeDao;
	}


	public void setAttributeDao(AttributeDao attributeDao) {
		this.attributeDao = attributeDao;
	}


	public List<AttributeDTO> list(String type) {
		return this.attributeDao.list(type);
	}

}
