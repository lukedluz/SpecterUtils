package com.lucas.specterutils.Eventos;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.lucas.specterutils.Main;

public class EventosUtils implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void aoCair(EntityChangeBlockEvent e) {
		if (e.getEntityType() == EntityType.FALLING_BLOCK && e.getTo() == Material.AIR) {
			if (e.getBlock().getType() == Material.ANVIL || e.getBlock().getType() == Material.GRAVEL
					|| e.getBlock().getType() == Material.SAND) {
				e.setCancelled(true);
				e.getBlock().getState().update(false, false);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoCairFolha(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onjoin(PlayerJoinEvent e) {
		if (e.getPlayer().getName().contains("Atlas") || e.getPlayer().getName().contains("LurionK")) {
			e.getPlayer().kickPlayer("�cVoc� n�o pode entrar com esse nick");
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockFromTo(BlockFromToEvent e) {
		if (e.getBlock().getType() == Material.SNOW) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.NAME_TAG) {
			String oldName = e.getRightClicked().getCustomName();
			if (e.getRightClicked().hasMetadata("pombamasmorra") || e.getRightClicked().hasMetadata("atlas_spawners_quantidade")) {
				new BukkitRunnable() {
					@Override
					public void run() {
						e.getRightClicked().setCustomName(oldName);
					}
				}.runTaskLater(Main.m, 1);
			}
		}
	}
	
	@EventHandler
	public void onCactus(BlockGrowEvent e) {
		if (e.getNewState().getType() == Material.CACTUS) {
			Block block1 = e.getNewState().getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock();
			Block block2 = e.getNewState().getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock();
			Block block3 = e.getNewState().getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock();
			Block block4 = e.getNewState().getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock();
			if (block1 != null && block1.getType() != Material.AIR || block2 != null && block2.getType() != Material.AIR || block3 != null && block3.getType() != Material.AIR || block4 != null && block4.getType() != Material.AIR) {
				e.setCancelled(true);
				e.getNewState().getBlock().getLocation().getWorld().dropItemNaturally(e.getNewState().getBlock().getLocation(), new ItemStack(Material.CACTUS));
			}
		}
	}
}
