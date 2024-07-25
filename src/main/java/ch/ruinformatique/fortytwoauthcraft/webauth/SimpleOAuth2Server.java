package ch.ruinformatique.fortytwoauthcraft.webauth;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ch.ruinformatique.fortytwoauthcraft.ConfigHandler;
import ch.ruinformatique.fortytwoauthcraft.LoggerHandler;
import ch.ruinformatique.fortytwoauthcraft.State;
import ch.ruinformatique.fortytwoauthcraft.managers.StateValidationManager;
import ch.ruinformatique.fortytwoauthcraft.managers.PlayerVerificationManager;

public class SimpleOAuth2Server {
	private final JavaPlugin plugin;
	private static final String oauth2ClientId = ConfigHandler.config.getString("oauth2_client_id");
	private static final String oauth2ClientSecret = ConfigHandler.config.getString("oauth2_client_secret");
	private static final String oauth2RedirectUri = ConfigHandler.config.getString("oauth2_redirect_uri");

	public SimpleOAuth2Server(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public static String getOauth2ClientId() {
		return oauth2ClientId;
	}

	public static String getOauth2ClientSecret() {
		return oauth2ClientSecret;
	}

	public static String getOauth2RedirectUri() {
		return oauth2RedirectUri;
	}

	private static HttpServer server;

	public void Start() throws IOException, URISyntaxException {
		server = HttpServer.create(new InetSocketAddress(8080), 0);
		server.createContext((new URI(oauth2RedirectUri).getPath()), new HttpHandler() {
			@Override
			public void handle(HttpExchange exchange) throws IOException {
				if ("GET".equals(exchange.getRequestMethod())) {
					URI requestURI = exchange.getRequestURI();
					String query = requestURI.getQuery();
					String[] params = query.split("&");
					String code = null;
					String state = null;
					for (String param : params) {
						if (param.startsWith("code=")) {
							code = param.split("=")[1];
						} else if (param.startsWith("state=")) {
							state = param.split("=")[1];
						}
					}
					State stateValidation = StateValidationManager.getState(state);
					if (stateValidation == null) {
						exchange.sendResponseHeaders(405, -1);
						return;
					}
					URI uri;
					try {
						uri = new URI("https://api.intra.42.fr/oauth/token");
					} catch (URISyntaxException e) {
						e.printStackTrace();
						exchange.sendResponseHeaders(405, -1);
						return;
					}
					URL url = uri.toURL();
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);

					String body = "grant_type=authorization_code"
							+ "&client_id=" + oauth2ClientId
							+ "&client_secret=" + oauth2ClientSecret
							+ "&code=" + code
							+ "&state=" + state
							+ "&redirect_uri=" + oauth2RedirectUri;

					try (OutputStream os = connection.getOutputStream()) {
						byte[] input = body.getBytes("utf-8");
						os.write(input, 0, input.length);
					}

					int responseCode = connection.getResponseCode();
					if (responseCode != 200) {
						exchange.sendResponseHeaders(405, -1);
						return;
					}

					String response = ConfigHandler.config.getString("oauth2_login_success_message");
					exchange.sendResponseHeaders(200, response.length());
					OutputStream os = exchange.getResponseBody();
					os.write(response.getBytes());
					os.close();
					Bukkit.getScheduler().runTask(plugin, () -> {
						PlayerVerificationManager.verifyPlayer(stateValidation.getPlayer());
					});
				} else {
					exchange.sendResponseHeaders(405, -1);
				}
			}
		});
		server.setExecutor(null);
		server.start();
		LoggerHandler.logger.info("OAuth2 server started on port " + server.getAddress().getPort());
	}

	public static void Stop() {
		server.stop(0);
	}

	public static boolean isRunning() {
		return server != null;
	}
}