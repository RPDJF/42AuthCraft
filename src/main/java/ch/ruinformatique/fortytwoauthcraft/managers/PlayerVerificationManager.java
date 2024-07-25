package ch.ruinformatique.fortytwoauthcraft.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

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

	public static void verifyPlayer(UUID player) {
		removeUnverifiedPlayer(player);
		Player playerObj = Bukkit.getPlayer(player);
		playerObj.sendMessage(ChatColor.GREEN + "You have been logged in successfully.");
		EventsManager.spawnFirework(playerObj.getLocation());
	}

	public static void askPlayerVerification(UUID player) {
		Player playerObj = Bukkit.getPlayer(player);
		playerObj.sendMessage(ChatColor.RED + "You need to login to play on this server.");
		playerObj.sendMessage(ChatColor.YELLOW + "Use /login to get the login URL.");

	}

	public static void clearUnverifiedPlayers() {
		unverifiedPlayers.clear();
	}

	public static boolean isPlayerVerified(UUID player) {
		return !unverifiedPlayers.contains(player);
	}
}