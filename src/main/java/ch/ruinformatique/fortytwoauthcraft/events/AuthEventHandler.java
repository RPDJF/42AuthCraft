package ch.ruinformatique.fortytwoauthcraft.events;

import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityDamageEvent;

// This class is an event handler for the plugin. It listens for various events and cancels them if the player is unverified.
public class AuthEventHandler implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerVerificationManager.addUnverifiedPlayer(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		PlayerVerificationManager.removeUnverifiedPlayer(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(!PlayerVerificationManager.isPlayerVerified(((Player) event.getEntity()).getUniqueId()));
		}
	}

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(!PlayerVerificationManager.isPlayerVerified(((Player) event.getEntity()).getUniqueId()));
		}
	}
}
