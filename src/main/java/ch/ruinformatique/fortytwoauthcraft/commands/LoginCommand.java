package ch.ruinformatique.fortytwoauthcraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.ruinformatique.fortytwoauthcraft.managers.EventsManager;
import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;
import ch.ruinformatique.fortytwoauthcraft.State;
import ch.ruinformatique.fortytwoauthcraft.managers.StateValidationManager;
import ch.ruinformatique.fortytwoauthcraft.webauth.SimpleOAuth2Server;

public class LoginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command");
			return true;
		}
		Player player = (Player) sender;
		if (PlayerVerificationManager.isPlayerVerified(player.getUniqueId())) {
			sender.sendMessage(ChatColor.GREEN + "You are already logged in");
			EventsManager.spawnFirework(player.getLocation());
			return true;
		}

		if (!StateValidationManager.stateExists(player.getUniqueId())) {
			StateValidationManager.addState(new State(player.getUniqueId()));
		}

		String url = "https://api.intra.42.fr/oauth/authorize?client_id=" + SimpleOAuth2Server.getOauth2ClientId()
				+ "&redirect_uri=" + SimpleOAuth2Server.getOauth2RedirectUri() + "&response_type=code&state="
				+ StateValidationManager.getState(player.getUniqueId()).getState();

		sender.sendMessage(ChatColor.GREEN + "Please login at " + ChatColor.AQUA + ChatColor.UNDERLINE + url);

		return true;
	}
}
