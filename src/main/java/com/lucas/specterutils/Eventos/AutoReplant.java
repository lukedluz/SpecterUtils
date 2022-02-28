package com.lucas.specterutils.Eventos;

import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.NetherWarts;

import com.intellectualcrafters.plot.object.Plot;

public class AutoReplant implements Listener {
	
	@EventHandler
	public void onBreak2(BlockBreakEvent e) {
		Player p = e.getPlayer();
		com.intellectualcrafters.plot.object.Location locp = new com.intellectualcrafters.plot.object.Location(
				e.getBlock().getWorld().getName(), (int) e.getBlock().getLocation().getX(),
				(int) e.getBlock().getLocation().getY(), (int) e.getBlock().getLocation().getZ());
		if (Plot.getPlot(locp) != null) {
			Plot plot = Plot.getPlot(locp);
			if (plot.isOwner(p.getUniqueId()) || plot.isAdded(p.getUniqueId())) {
				if (e.getBlock().getType() == Material.NETHER_WARTS) {
					NetherWarts fungo = (NetherWarts) e.getBlock().getState().getData();
					if (fungo.getState() == NetherWartsState.RIPE) {
						e.setCancelled(true);
						e.getBlock().setType(Material.NETHER_WARTS);
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.NETHER_STALK, 3));
					} else {
						e.setCancelled(true);
						e.getBlock().setType(Material.AIR);
						e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(),
								new ItemStack(Material.NETHER_STALK, 1));
					}
				}
			}
		}
	}
}
