package ch.ruinformatique.fortytwoauthcraft;

import org.bukkit.configuration.Configuration;

public class ConfigHandler {
	public static final Configuration config = FortytwoAuthCraft.getPlugin().getConfig();

	public static boolean isConfigured() {
		if (config.getString("oauth2_client_id") == null || config.getString("oauth2_client_id").isEmpty()
				|| config.getString("oauth2_client_id").equals("your-uid")) {
			return false;
		} else if (config.getString("oauth2_client_secret") == null
				|| config.getString("oauth2_client_secret").isEmpty()
				|| config.getString("oauth2_client_secret").equals("your-secret")) {
			return false;
		} else if (config.getString("oauth2_redirect_uri") == null
				|| config.getString("oauth2_redirect_uri").isEmpty()) {
			return false;
		} else if (config.getString("login_prompt_message") == null
				|| config.getString("login_prompt_message").isEmpty()) {
			return false;
		} else if (config.getString("login_command_example_message") == null
				|| config.getString("login_command_example_message").isEmpty()) {
			return false;
		} else if (config.getString("login_url_message") == null || config.getString("login_url_message").isEmpty()) {
			return false;
		} else if (config.getString("login_success_message") == null
				|| config.getString("login_success_message").isEmpty()) {
			return false;
		} else if (config.getString("login_already_logged_in_message") == null
				|| config.getString("login_already_logged_in_message").isEmpty()) {
			return false;
		} else if (config.getString("oauth2_login_success_message") == null
				|| config.getString("oauth2_login_success_message").isEmpty()) {
			return false;
		}
		return true;
	}
}
