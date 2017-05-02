package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MasteryTreeList extends Dto implements Serializable {

	private static final long serialVersionUID = -2040839715528403925L;

	private List<MasteryTreeItem> masteryTreeItems;

	public List<MasteryTreeItem> getMasteryTreeItems() {
		return masteryTreeItems;
	}

	@Override
	public String toString() {
		return getMasteryTreeItems().toString();
	}
}
