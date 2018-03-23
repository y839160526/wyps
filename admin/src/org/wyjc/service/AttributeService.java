package org.wyjc.service;

import java.util.List;

import org.wyjc.entity.AttributeDTO;

public interface AttributeService {
	public List<AttributeDTO> list(String type);

}
