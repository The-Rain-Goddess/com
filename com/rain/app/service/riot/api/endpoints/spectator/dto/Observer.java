package com.rain.app.service.riot.api.endpoints.spectator.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Observer extends Dto implements Serializable {

	private static final long serialVersionUID = 1866998515867420059L;

	private String encryptionKey;

	public String getEncryptionKey() {
		return encryptionKey;
	}

	@Override
	public String toString() {
		return getEncryptionKey();
	}
}
