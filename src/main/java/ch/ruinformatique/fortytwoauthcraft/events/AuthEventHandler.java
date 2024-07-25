package ch.ruinformatique.fortytwoauthcraft.events;

import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;

import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

// This class is an event handler for the plugin. It listens for various events and cancels them if the player is unverified.
public class AuthEventHandler implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId())) {
			Player player = event.getPlayer();
			Location from = event.getFrom();
			Location to = event.getTo();
			if (from.getX() != to.getX() || from.getZ() != to.getZ() || from.getY() < to.getY()) {
				player.teleport(from);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		PlayerVerificationManager.addUnverifiedPlayer(event.getPlayer().getUniqueId());
		PlayerVerificationManager.askPlayerVerification(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		PlayerVerificationManager.removeUnverifiedPlayer(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
			PlayerVerificationManager.askPlayerVerification(event.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
			PlayerVerificationManager.askPlayerVerification(event.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void onPlayerItemDamage(PlayerItemDamageEvent event) {
		event.setCancelled(!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId()));
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (!PlayerVerificationManager.isPlayerVerified(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
			PlayerVerificationManager.askPlayerVerification(event.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (!PlayerVerificationManager.isPlayerVerified(((Player) event.getEntity()).getUniqueId())) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(!PlayerVerificationManager.isPlayerVerified(((Player) event.getEntity()).getUniqueId()));
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Firework) {
			Firework firework = (Firework) event.getDamager();
			if (firework.hasMetadata("noDamage")) {
				event.setCancelled(true);
			}
		} else if (event.getDamager() instanceof Player) {
			if (!PlayerVerificationManager.isPlayerVerified(((Player) event.getDamager()).getUniqueId())) {
				event.setCancelled(true);
				PlayerVerificationManager.askPlayerVerification(event.getDamager().getUniqueId());
			}
		}
	}
}
