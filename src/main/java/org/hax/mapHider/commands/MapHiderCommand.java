package org.hax.mapHider.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.hax.mapHider.MapHider;

public class MapHiderCommand implements CommandExecutor {
    private final MapHider plugin;

    public MapHiderCommand(MapHider plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("maphider.reload")) {
                plugin.reloadConfig();
                sender.sendMessage("§a[MapHider] Configuration reloaded successfully!");
                plugin.getLogger().info("Configuration reloaded by " + sender.getName());
                return true;
            } else {
                sender.sendMessage("§cYou do not have permission to run this command.");
                return true;
            }
        }

        sender.sendMessage("§eUsage: /maphider reload");
        return true;
    }
}
