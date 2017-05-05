package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class ItemList extends Dto implements Serializable {

	private static final long serialVersionUID = -1708490617004185357L;

	private BasicData basic;
	private Map<String, Item> data;
	private List<Group> groups;
	private List<ItemTree> tree;
	private String type;
	private String version;

	public BasicData getBasic() {
		return basic;
	}

	public Map<String, Item> getData() {
		return data;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public List<ItemTree> getTree() {
		return tree;
	}

	public String getType() {
		return type;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return getType();
	}
}
