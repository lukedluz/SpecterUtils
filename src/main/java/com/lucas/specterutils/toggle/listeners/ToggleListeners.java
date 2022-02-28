package net.hubtoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.hubtoggle.Main;
import net.hubtoggle.commands.ToggleCommand;
import net.hubtoggle.object.Toggle;

public class ToggleListeners implements Listener {

	@EventHandler
	public void join(PlayerJoinEvent e) {
		if (!Main.instance.cache.containsKey(e.getPlayer().getName())) {
			Main.instance.cache.put(e.getPlayer().getName(),
					new Toggle(e.getPlayer().getName(), true, true, true, true));
		}
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("Preferências")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (!e.getCurrentItem().hasItemMeta())
				return;
			Toggle t = Main.instance.cache.get(p.getName());
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eTell")) {
				if (t.isTell()) {
					t.setTell(false);
				}else {
					t.setTell(true);
				}
				ToggleCommand.openInv(p);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eTpa")) {
				if (t.isTpa()) {
					t.setTpa(false);
				}else {
					t.setTpa(true);
				}
				ToggleCommand.openInv(p);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eCoins")) {
				if (t.isCoins()) {
					t.setCoins(false);
				}else {
					t.setCoins(true);
				}
				ToggleCommand.openInv(p);
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eGlobal")) {
				if (t.isGlobal()) {
					t.setGlobal(false);
				}else {
					t.setGlobal(true);
				}
				ToggleCommand.openInv(p);
			}
		}
	}

}
