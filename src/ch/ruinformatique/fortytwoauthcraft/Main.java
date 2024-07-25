package ch.ruinformatique.fortytwoauthcraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.*;

import ch.ruinformatique.fortytwoauthcraft.commands.*;
import ch.ruinformatique.fortytwoauthcraft.events.*;
import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("42AuthCraft enabled");
		PlayerVerificationManager.clearUnverifiedPlayers();
		Bukkit.getOnlinePlayers()
				.forEach(player -> PlayerVerificationManager.addUnverifiedPlayer(player.getUniqueId()));
		getCommand("login").setExecutor(new LoginCommand());
		getServer().getPluginManager().registerEvents(new AuthEventHandler(), this);
	}

	@Override
	public void onDisable() {
		System.out.println("42AuthCraft disabled");
		super.onDisable();
	}
}
