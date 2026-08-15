# MapHider for Pl3xMap

A lightweight, performance-friendly Paper plugin that automatically hides players from the Pl3xMap web interface when they venture underground. 

By hooking directly into the Pl3xMap API, this plugin seamlessly manages player visibility without relying on console command dispatching. This prevents console spam, reduces server overhead, and ensures instantaneous map updates.

---

## Features

* **Automatic Depth Detection:** Dynamically hides players when their Y-coordinate falls below sea level (Y < 62) and their block's sky light level reaches 0.
* **Direct API Hook:** Modifies the internal Pl3xMap `PlayerRegistry` directly for zero-overhead state changes.
* **Clean Unloading:** Automatically reveals all hidden players if the plugin is disabled or the server restarts, preventing players from being permanently stuck off-map.
* **Live Reloading:** Toggle debug logging on the fly without needing to restart your server or reload the mapping engine.

---

## Requirements

* **Server Software:** Paper (or Bukkit/Spigot) 1.21+
* **Java:** Java 17 or higher
* **Dependencies:** [Pl3xMap](https://modrinth.com/plugin/pl3xmap) (1.21.11-544 or compatible)

---

## Installation

1. Ensure **Pl3xMap** is installed and functioning perfectly on your server.
2. Download the compiled `MapHider.jar` file from the releases page.
3. Place the `.jar` file into your server's `plugins/` directory.
4. Restart your server to generate the configuration files.

---

## Configuration

The default `config.yml` is generated automatically in `plugins/MapHider/` upon the first startup. 

```yaml
# MapHider Configuration File

# If true, the plugin will log a message to the console every time a player is hidden or shown.
debug: false
