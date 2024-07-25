# 42AuthCraft 🎮🔒

Welcome to **42AuthCraft**! This is a simple and lightweight authentication plugin for Minecraft Spigot servers (version 1.21.X). Designed specifically for the 42 Network students, it allows you to authenticate on a Minecraft server using your 42 intranet credentials via OAuth2.

## Features ✨

- **Simple & Lightweight**: No sensitive data is stored.
- **OAuth2 Authentication**: Authenticate using your 42 intranet credentials.
- **Session-Based**: Players need to re-authenticate each time they join the server.
- **Maven Managed**: Dependencies are managed using Maven.

## How It Works 🛠️

1. **Join the Server**: When a player joins the server, they are added to the list of unauthenticated players.
2. **Authenticate**: The player must use the `/login` command, which provides a link to authenticate via the 42 intranet.
3. **Play**: Once authenticated, the player is removed from the unauthenticated list and can play normally for the current session.
4. **Re-authentication**: Players must re-authenticate each time they join the server. If the server is reloaded, all connected players will need to re-authenticate.

## Getting Started 🚀

1. **Clone the Repository**: `git clone https://github.com/yourusername/42AuthCraft.git`
2. **Build with Maven**: Navigate to the project directory and run `mvn clean install`.
3. **Deploy**: Place the generated JAR file in your Spigot server's plugins directory.
4. **Configure**: Set up your OAuth2 credentials in the plugin configuration file.
5. **Run the Server**: Start your Spigot server and enjoy!

## Contributing 🤝

This is my first Java project and my first Minecraft plugin, so please be kind! Feel free to contribute by opening issues or submitting pull requests.

## License 📜

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements 🙏

- Special thanks to the Spigot community for their invaluable resources and tutorials.

---

Happy crafting! 🛠️🎉