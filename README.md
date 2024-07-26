# 42AuthCraft 🎮🔒

Welcome to **42AuthCraft**, a minecraft plugin to restrict access to 42 Students 🔒
This is a simple and lightweight authentication plugin for Minecraft Spigot servers (version 1.21.X). Designed specifically for the 42 Network students, it allows you to authenticate on a Minecraft server using your 42 intranet credentials via OAuth2.

## Preview 🎥
![Preview](https://github.com/RPDJF/42AuthCraft/blob/main/docs/preview.gif?raw=true)

## Proof of concept server 🚀
A minecraft server restricted to 42 students that you can join

**ip**: `play.ruinfo.ch`

## Features ✨

- **Simple & Lightweight**: No sensitive data is stored.
- **OAuth2 Authentication**: Authenticate using your 42 intranet credentials.
- **Session-Based**: Players need to re-authenticate each time they join the server.
- **Customizable**: Replace the default messages with your own.

## Upcoming Features (maybe) 🚀
- **Nickname Sync**: Automatically set the player's nickname to their 42 username if configured in the `config.yml`.

## How It Works 🛠️

1. **Join the Server**: When a player joins the server, they are added to the list of unauthenticated players.
2. **Authenticate**: The player must use the `/login` command, which provides a link to authenticate via the 42 intranet.
3. **Play**: Once authenticated, the player is removed from the unauthenticated list and can play normally for the current session.
4. **Re-authentication**: Players must re-authenticate each time they join the server. If the server is reloaded, all connected players will need to re-authenticate.

## Getting Started 🚀

1. **Get 42 API keys**: You will need to create an OAuth2 application on the 42 intranet to get your client ID and client secret [here](https://profile.intra.42.fr/oauth/applications/new).
2. **Set up the Redirect URI**: Set the redirect URI to your server on the 42 intranet application settings. By default, the redirect URI is `http://localhost:8080/oauth2/callback`, I recommend using a reverse proxy and domain name to redirect to your server.
2. **Download the Plugin**: Download the latest release from the github repository.
3. **Install the Plugin**: Place the downloaded `.jar` file in the `plugins` folder of your Spigot server.
4. **Start the Server**: Start your server and wait for the plugin to load.
5. **Configure the Plugin**: Open the `config.yml` file in the `42AuthCraft` folder and set your client ID and client secret.
6. **Reload the Server**: Reload the server to apply the changes.
7. **Play**: Join the server and use the `/login` command to authenticate.

## Development 🛠️ (Local development)
1. **Clone the Repository**: Clone the repository to your local machine.
2. **Set up the 42 API keys**: Follow the steps above to get your client ID and client secret.
3. **Set up the Redirect URI**: Set the redirect URI to `http://localhost:8080/oauth2/callback` on the 42 intranet application settings.
4. **Compile with Maven**: Run `mvn clean install` to compile the plugin.

## Contributing 🤝

This is my first Java project and my first Minecraft plugin, so please be kind! Feel free to contribute by opening issues or submitting pull requests.

## License 📜

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements 🙏

- Special thanks to the Spigot community for their invaluable resources and tutorials.

---

Happy crafting! 🛠️🎉
