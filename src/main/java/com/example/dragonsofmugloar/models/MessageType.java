package com.example.dragonsofmugloar.models;

import lombok.Getter;

@Getter
public enum MessageType {
	HELP("Help", 40),
	ESCORT("Escort", 30),
	CREATE("Create", 20),
	STEAL("Steal", 10),
	UNKNOWN("Unknown", 0);

	private final String label;
	private final int value;

	MessageType(String label, int value) {
		this.label = label;
		this.value = value;
	}

}
