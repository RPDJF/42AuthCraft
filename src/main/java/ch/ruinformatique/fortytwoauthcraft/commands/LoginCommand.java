package ch.ruinformatique.fortytwoauthcraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;
import ch.ruinformatique.fortytwoauthcraft.FortytwoAuthCraft;

public class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command");
			return true;
		}
		Player player = (Player) sender;
		sender.sendMessage(ChatColor.GREEN + "You have been logged in");
		player.setDisplayName(ChatColor.RED + "test");
		PlayerVerificationManager.removeUnverifiedPlayer(player.getUniqueId());

		Location spawnLocation = player.getLocation();
		FireworkMeta fireworkMeta = (FireworkMeta) Bukkit.getItemFactory()
				.getItemMeta(org.bukkit.Material.FIREWORK_ROCKET);

		fireworkMeta.setPower(2);
		fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

		for (int i = 0; i < 6; i++) {
			Firework firework = (Firework) player.getWorld().spawnEntity(spawnLocation, EntityType.FIREWORK_ROCKET);
			firework.setFireworkMeta(fireworkMeta);
			firework.setMetadata("noDamage", new FixedMetadataValue(FortytwoAuthCraft.getPlugin(), true));
			if (i == 0) {
				firework.detonate();
			}
		}

		return true;
	}
}
