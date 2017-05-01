package com.rain.app.service.riot.api.endpoints.status.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;

public class Incident extends Dto implements Serializable {

	private static final long serialVersionUID = -5984477375688730952L;

	private boolean active;
	@SerializedName(value = "created_at")
	private String createdAt;
	private long id;
	private List<Message> updates;

	public String getCreatedAt() {
		return createdAt;
	}

	public long getId() {
		return id;
	}

	public List<Message> getUpdates() {
		return updates;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public String toString() {
		return String.valueOf(getId());
	}
}
