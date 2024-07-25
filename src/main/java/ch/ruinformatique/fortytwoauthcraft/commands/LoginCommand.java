package ch.ruinformatique.fortytwoauthcraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;

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
		return true;
	}
}
