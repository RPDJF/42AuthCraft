package ch.ruinformatique.fortytwoauthcraft.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;

public class PlayerVerificationManager {
	private static final List<UUID> unverifiedPlayers = new ArrayList<>();

	public static void addUnverifiedPlayer(UUID player) {
		unverifiedPlayers.add(player);
		Bukkit.getPlayer(player).setAllowFlight(true);
	}

	public static void removeUnverifiedPlayer(UUID player) {
		unverifiedPlayers.remove(player);
		Bukkit.getPlayer(player).setAllowFlight(false);
	}

	public static void clearUnverifiedPlayers() {
		unverifiedPlayers.clear();
	}

	public static boolean isPlayerVerified(UUID player) {
		return !unverifiedPlayers.contains(player);
	}
}