package ch.ruinformatique.fortytwoauthcraft;

import java.io.IOException;
import java.net.URISyntaxException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.*;

import ch.ruinformatique.fortytwoauthcraft.commands.*;
import ch.ruinformatique.fortytwoauthcraft.events.*;
import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;
import ch.ruinformatique.fortytwoauthcraft.webauth.SimpleOAuth2Server;

public class FortytwoAuthCraft extends JavaPlugin {

	public static FortytwoAuthCraft getPlugin() {
		return JavaPlugin.getPlugin(FortytwoAuthCraft.class);
	}

	@Override
	public void onEnable() {
		System.out.println("42AuthCraft enabled");
		SimpleOAuth2Server oauth2Server = new SimpleOAuth2Server(this);
		try {
			oauth2Server.Start();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}
		PlayerVerificationManager.clearUnverifiedPlayers();
		Bukkit.getOnlinePlayers()
				.forEach(player -> {
					PlayerVerificationManager.addUnverifiedPlayer(player.getUniqueId());
					PlayerVerificationManager.askPlayerVerification(player.getUniqueId());
				});
		getCommand("login").setExecutor(new LoginCommand());
		getServer().getPluginManager().registerEvents(new AuthEventHandler(), this);
		super.onEnable();
	}

	@Override
	public void onDisable() {
		System.out.println("42AuthCraft disabled");
		SimpleOAuth2Server.Stop();
		super.onDisable();
	}
}
