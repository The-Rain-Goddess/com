package com.rain.app.service.riot.api.endpoints.status.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.rain.app.service.riot.api.Dto;




public class Message extends Dto implements Serializable {

	private static final long serialVersionUID = 9036838901306467780L;

	private String author;
	private String content;
	@SerializedName(value = "created_at")
	private String createdAt;
	private String id;
	private String severity;
	private List<Translation> translations;
	@SerializedName(value = "updated_at")
	private String updatedAt;

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getId() {
		return id;
	}

	public String getSeverity() {
		return severity;
	}

	public List<Translation> getTranslations() {
		return translations;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return getContent();
	}
}
