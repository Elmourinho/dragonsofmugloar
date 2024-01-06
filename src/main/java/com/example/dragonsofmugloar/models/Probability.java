package com.example.dragonsofmugloar.models;

import lombok.Getter;

@Getter
public enum Probability {
	SURE_THING("Sure thing", 1000),
	PIECE_OF_CAKE("Piece of cake", 900),
	WALK_IN_THE_PARK("Walk in the park", 800),
	QUITE_LIKELY("Quite likely", 700),
	HMMM("Hmmm....", 500),
	GAMBLE("Gamble", 400),
	RATHER_DETRIMENTAL("Rather detrimental", 300),
	RISKY("Risky", 200),
	PLAYING_WITH_FIRE("Playing with fire", 100),
	IMPOSSIBLE("Impossible", 0),
	SUICIDE_MISSION("Suicide mission", 0),
	UNKNOWN("Unknown", 0);

	private final String label;
	private final int value;

	Probability(String label, int value) {
		this.label = label;
		this.value = value;
	}

}
