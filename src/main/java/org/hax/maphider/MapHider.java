package org.hax.maphider;

import org.bukkit.plugin.java.JavaPlugin;
import org.hax.maphider.commands.ReloadCommand;
import org.hax.maphider.util.VisibilityTask;

public class MapHider extends JavaPlugin {

    private VisibilityTask visibilityTask;

    @Override
    public void onEnable() {
        // Config.yml generate
        saveDefaultConfig();

        getCommand("maphider").setExecutor(new ReloadCommand(this));

        visibilityTask = new VisibilityTask(this);
        visibilityTask.runTaskTimer(this, 0L, 100L);

        getLogger().info("MapHider hooked into Pl3xMap successfully.");
    }

    @Override
    public void onDisable() {
        if (visibilityTask != null && !visibilityTask.isCancelled()) {
            visibilityTask.cancel();
        }

        VisibilityTask.revealAll(this);
        getLogger().info("MapHider disabled. All players revealed.");
    }
}