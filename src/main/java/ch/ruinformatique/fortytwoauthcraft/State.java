package ch.ruinformatique.fortytwoauthcraft;

import java.util.UUID;

public class State {
	private String state;
	private UUID player;

	public State(String state, UUID player) {
		this.state = state;
		this.player = player;
	}

	public State(UUID player) {
		long currentTimeMillis = System.currentTimeMillis();
		String randomUUID = UUID.randomUUID().toString();
		this.state = currentTimeMillis + "-" + randomUUID;
		this.player = player;
	}

	public String getState() {
		return state;
	}

	public UUID getPlayer() {
		return player;
	}
}
