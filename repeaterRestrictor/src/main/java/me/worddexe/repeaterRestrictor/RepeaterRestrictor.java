package me.worddexe.repeaterRestrictor;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RepeaterRestrictor extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("RepeaterRestrictor enabled.");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RepeaterRestrictor disabled.");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        if (block.getType() != Material.REPEATER) {
            return;
        }

        World world = block.getWorld();

        if (!world.getEnvironment().equals(World.Environment.NETHER)) {
            return;
        }

        if (block.getY() >= 128) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cYou cannot place a repeater on or above the Nether roof!");
        }
    }
}
