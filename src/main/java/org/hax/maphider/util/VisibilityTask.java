package org.hax.maphider.util;

import net.pl3x.map.core.Pl3xMap;
import net.pl3x.map.core.player.Player;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.hax.maphider.MapHider;

public class VisibilityTask extends BukkitRunnable{
    private final MapHider plugin;

    public VisibilityTask(MapHider plugin) {
        this.plugin = plugin;
    }

    public void run() {
        if (!Pl3xMap.api().isEnabled()) return;

        boolean debug = plugin.getConfig().getBoolean("debug", false);

        int depthThreshold = plugin.getConfig().getInt("depth-threshold", 8);

        for (org.bukkit.entity.Player bukkitPlayer : plugin.getServer().getOnlinePlayers()) {
            checkAndToggleVisibility(bukkitPlayer, debug, depthThreshold);
        }
    }

    private void checkAndToggleVisibility(org.bukkit.entity.Player bukkitPlayer, boolean debug, int depthThreshold) {
        Location loc = bukkitPlayer.getLocation();

        int highestYLevel = loc.getWorld().getHighestBlockYAt(loc);

        double depthFromSurface = highestYLevel - loc.getY();

        // Define "underground" as 0 skylight and below a certain level below the highest surface possible to the player
        boolean isUnderground = loc.getBlock().getLightFromSky() == 0 && depthFromSurface < depthThreshold;

        // Fetch the player from Pl3xMap's registry
        Player mapPlayer = Pl3xMap.api().getPlayerRegistry().get(bukkitPlayer.getUniqueId());

        if (mapPlayer != null) {
            // Only update the API if the state is actually changing
            if (isUnderground && !mapPlayer.isHidden()) {
                mapPlayer.setHidden(true, false);
                if (debug) {
                    plugin.getLogger().info("[DEBUG] Hiding " + mapPlayer.getName() + " (Went underground)");
                }
            } else if (!isUnderground && mapPlayer.isHidden()) {
                mapPlayer.setHidden(false, false);
                if (debug) {
                    plugin.getLogger().info("[DEBUG] Showing " + mapPlayer.getName() + " (Returned to Surface)");
                }
            }
        }
    }

    public static void revealAll(MapHider plugin) {
        if (Pl3xMap.api().isEnabled()) {
            for (org.bukkit.entity.Player bukkitPlayer : plugin.getServer().getOnlinePlayers()) {
                Player mapPlayer = Pl3xMap.api().getPlayerRegistry().get(bukkitPlayer.getUniqueId());
                if (mapPlayer != null) {
                    mapPlayer.setHidden(false, false);
                }
            }
        }
    }
}
