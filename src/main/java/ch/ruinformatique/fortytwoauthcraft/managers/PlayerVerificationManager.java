package ch.ruinformatique.fortytwoauthcraft.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ch.ruinformatique.fortytwoauthcraft.ConfigHandler;

public class PlayerVerificationManager {
	private static final List<UUID> unverifiedPlayers = new ArrayList<>();

	public static void addUnverifiedPlayer(UUID player) {
		unverifiedPlayers.add(player);
	}

	public static void removeUnverifiedPlayer(UUID player) {
		unverifiedPlayers.remove(player);
	}

	public static void verifyPlayer(UUID player) {
		removeUnverifiedPlayer(player);
		Player playerObj = Bukkit.getPlayer(player);
		playerObj.sendMessage(ConfigHandler.config.getString("login_success_message"));
		EventsManager.spawnFirework(playerObj.getLocation());
	}

	public static void askPlayerVerification(UUID player) {
		Player playerObj = Bukkit.getPlayer(player);
		playerObj.sendMessage(ConfigHandler.config.getString("login_prompt_message"));
		playerObj.sendMessage(ConfigHandler.config.getString("login_command_example_message"));
	}

	public static void clearUnverifiedPlayers() {
		unverifiedPlayers.clear();
	}

	public static boolean isPlayerVerified(UUID player) {
		return !unverifiedPlayers.contains(player);
	}
}