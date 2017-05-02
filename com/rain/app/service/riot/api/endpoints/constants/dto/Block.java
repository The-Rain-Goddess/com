package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

import net.rithms.riot.dto.Static.BlockItem;

public class Block extends Dto implements Serializable {

	private static final long serialVersionUID = -4041031408263653499L;

	private List<BlockItem> items;
	private boolean recMath;
	private String type;

	public List<BlockItem> getItems() {
		return items;
	}

	public String getType() {
		return type;
	}

	public boolean isRecMath() {
		return recMath;
	}

	@Override
	public String toString() {
		return getType();
	}
}
