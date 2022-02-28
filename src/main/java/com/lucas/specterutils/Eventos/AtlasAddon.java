package com.lucas.specterutils.Eventos;

import java.util.HashMap;

import com.lucas.specterutils.API.CaptchaAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AtlasAddon implements Listener {

	private static HashMap<String, Integer> ClickCount = new HashMap<>();

	public static boolean hasClick(Player p) {
		if (ClickCount.containsKey(p.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static void addPClick(Player p, Integer valor) {
		ClickCount.put(p.getName(), valor);
	}

	public static void removePClick(Player p) {
		ClickCount.remove(p.getName());
	}

	public static int getClicks(Player p) {
		if (hasClick(p)) {
			return ClickCount.get(p.getName());
		} else {
			return 0;
		}
	}

	public static void addClicks(Player p, Integer valor) {
		if (getClicks(p) >= 30) {
			removePClick(p);
			addPClick(p, 0);
			p.closeInventory();
			CaptchaAPI.ExecuteCaptcha(p);
		} else {
			Integer clicks = getClicks(p);
			removePClick(p);
			addPClick(p, clicks + 1);
		}
	}

	@EventHandler
	public void onclick(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("Compra de Geradores")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				addClicks((Player) e.getWhoClicked(), 1);
			}
		}
		if (e.getInventory().getName().equalsIgnoreCase("M�quinas - Loja")) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getType() == Material.GOLD_INGOT) {
					addClicks((Player) e.getWhoClicked(), 1);
				}
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (hasClick(e.getPlayer())) {
			removePClick(e.getPlayer());
		}
	}
}
