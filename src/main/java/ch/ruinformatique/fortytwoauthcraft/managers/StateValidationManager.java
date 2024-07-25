package ch.ruinformatique.fortytwoauthcraft.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.ruinformatique.fortytwoauthcraft.State;

public class StateValidationManager {
	private static final List<State> states = new ArrayList<>();

	public static void addState(State state) {
		states.add(state);
		new Thread(() -> {
			try {
				Thread.sleep(60000);
				states.remove(state);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void removeState(String state) {
		for (State s : states) {
			if (s.getState().equals(state)) {
				states.remove(s);
				break;
			}
		}
	}

	public static void removeState(State state) {
		states.remove(state);
	}

	public static boolean stateExists(String state) {
		for (State s : states) {
			if (s.getState().equals(state)) {
				return true;
			}
		}
		return false;
	}

	public static boolean stateExists(State state) {
		return states.contains(state);
	}

	public static boolean stateExists(UUID player) {
		for (State s : states) {
			if (s.getPlayer().equals(player)) {
				return true;
			}
		}
		return false;
	}

	public static State getState(String state) {
		for (State s : states) {
			if (s.getState().equals(state)) {
				return s;
			}
		}
		return null;
	}

	public static State getState(UUID player) {
		for (State s : states) {
			if (s.getPlayer().equals(player)) {
				return s;
			}
		}
		return null;
	}
}
