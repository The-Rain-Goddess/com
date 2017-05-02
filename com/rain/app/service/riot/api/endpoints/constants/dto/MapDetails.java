package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MapDetails extends Dto implements Serializable {

	private static final long serialVersionUID = -8161048291003061062L;

	private Image image;
	private int mapId;
	private String mapName;
	private List<Long> unpurchasableItemList;

	public Image getImage() {
		return image;
	}

	public int getMapId() {
		return mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public List<Long> getUnpurchasableItemList() {
		return unpurchasableItemList;
	}

	@Override
	public String toString() {
		return getMapName();
	}
}
