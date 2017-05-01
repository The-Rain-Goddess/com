package com.rain.app.service.riot.api.endpoints.match.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MatchTimeline extends Dto implements Serializable {

	private static final long serialVersionUID = 3888184958883394435L;

	private long frameInterval;
	private List<MatchFrame> frames;

	public long getFrameInterval() {	
		return frameInterval;
	}

	public List<MatchFrame> getFrames() {
		return frames;
	}

	@Override
	public String toString() {
		return String.valueOf(getFrameInterval());
	}
}
