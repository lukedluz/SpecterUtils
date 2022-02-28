package com.lucas.specterutils.API;

import org.bukkit.entity.*;
import java.util.concurrent.*;
import org.bukkit.plugin.*;

import com.lucas.specterutils.Main;

import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.*;
import java.util.*;

public class CaptchaAPI implements Listener {
	
	private static Material CaptchaSolution;
	private static String CaptchaGUITitle;
	private static HashMap<Player, Integer> CaptchaFailCount;
	private static HashMap<Player, Boolean> CaptchaSolved;
	private static ArrayList<Material> RndmItem;

	public static void ExecuteCaptcha(Player p) {
		CaptchaAPI.CaptchaSolved.put(p, false);
		CaptchaAPI.CaptchaFailCount.putIfAbsent(p, 0);
		Main.m.getServer().getScheduler().runTaskAsynchronously((Plugin) Main.m, () -> {
			int slot = 10;
			CaptchaAPI.CaptchaSolution = CaptchaAPI.RndmItem
					.get(ThreadLocalRandom.current().nextInt(1, CaptchaAPI.RndmItem.size()));
			CaptchaAPI.CaptchaGUITitle = "�aClique no(a) " + ItemName.valueOf(CaptchaSolution).getName();
			Inventory CaptchaGUI = Bukkit.createInventory((InventoryHolder) null, 27, CaptchaAPI.CaptchaGUITitle);
			for (int i = 0; i < 7; ++i) {
				Material MaterialItem = CaptchaAPI.RndmItem.get(ThreadLocalRandom.current().nextInt(1, 7));
				CaptchaAPI.RndmItem.remove(MaterialItem);
				CaptchaGUI.setItem(slot, new ItemStack(MaterialItem, 1));
				++slot;
			}
			CaptchaAPI.RndmItem.clear();
			LoadItemArrayList();
			if (CaptchaGUI.contains(CaptchaAPI.CaptchaSolution)) {
				p.openInventory(CaptchaGUI);
				CaptchaAPI.CaptchaSolved.put(p, false);
			} else {
				int randomSlot = ThreadLocalRandom.current().nextInt(10, 16);
				CaptchaGUI.setItem(randomSlot, new ItemStack(CaptchaAPI.CaptchaSolution));
				p.openInventory(CaptchaGUI);
			}
		});
	}

	public static void LoadItemArrayList() {
		CaptchaAPI.RndmItem.add(Material.LADDER);
		CaptchaAPI.RndmItem.add(Material.HOPPER);
		CaptchaAPI.RndmItem.add(Material.MINECART);
		CaptchaAPI.RndmItem.add(Material.APPLE);
		CaptchaAPI.RndmItem.add(Material.ANVIL);
		CaptchaAPI.RndmItem.add(Material.ARROW);
		CaptchaAPI.RndmItem.add(Material.BOW);
		CaptchaAPI.RndmItem.add(Material.BOWL);
		CaptchaAPI.RndmItem.add(Material.BOOK);
		CaptchaAPI.RndmItem.add(Material.TORCH);
		CaptchaAPI.RndmItem.add(Material.STICK);
		CaptchaAPI.RndmItem.add(Material.STRING);
		CaptchaAPI.RndmItem.add(Material.EGG);
		CaptchaAPI.RndmItem.add(Material.FEATHER);
		CaptchaAPI.RndmItem.add(Material.EMERALD);
		CaptchaAPI.RndmItem.add(Material.DIAMOND);
		CaptchaAPI.RndmItem.add(Material.REDSTONE);
		CaptchaAPI.RndmItem.add(Material.BUCKET);
		CaptchaAPI.RndmItem.add(Material.PAPER);
		CaptchaAPI.RndmItem.add(Material.BONE);
		CaptchaAPI.RndmItem.add(Material.COMPASS);
		CaptchaAPI.RndmItem.add(Material.NAME_TAG);
	}

	@EventHandler
	public void CaptchaClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		CaptchaAPI.CaptchaSolved.putIfAbsent(p, true);
		if (event.getInventory() != null && event.getInventory().getName().contains("�aClique no(a) ")
				&& !CaptchaAPI.CaptchaSolved.get(p)) {
			if (event.getCurrentItem().getType().equals((Object) CaptchaAPI.CaptchaSolution)) {
				CaptchaAPI.CaptchaFailCount.put(p, 0);
				CaptchaAPI.CaptchaSolved.remove(p);
				p.closeInventory();
			} else {
				CaptchaAPI.CaptchaFailCount.put(p, CaptchaAPI.CaptchaFailCount.get(p) + 1);
				this.CheckFailCount(p);
			}
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void InventoryClose(InventoryCloseEvent event) {
		Player p = (Player) event.getPlayer();
		if (Objects.equals(event.getInventory().getName(), CaptchaAPI.CaptchaGUITitle)
				&& CaptchaAPI.CaptchaSolved.containsKey(p) && !CaptchaAPI.CaptchaSolved.get(p)) {
			CaptchaAPI.CaptchaFailCount.put(p, CaptchaAPI.CaptchaFailCount.get(p) + 1);
			this.CheckFailCount(p);
		}
	}

	private void CheckFailCount(Player p) {
		if (CaptchaAPI.CaptchaFailCount.get(p) >= 1) {
			for (Player pOnline : Bukkit.getOnlinePlayers()) {
				if (pOnline.hasPermission("nocheatplus.shortcut.info")) {
					pOnline.sendMessage("�c�lSUSPEITO �c" + p.getName() + " �7falhou no �cCaptcha �7(�c"
							+ CaptchaAPI.CaptchaFailCount.get(p) + "VL�7)");
				}
			}
		}
		if (CaptchaAPI.CaptchaFailCount.get(p) >= Main.m.getConfig().getInt("Captcha.Limite")) {
			CaptchaAPI.CaptchaFailCount.put(p, 0);
			CaptchaAPI.CaptchaSolved.remove(p);
			p.closeInventory();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.m.getConfig().getString("Captcha.Comando").replace("%p", p.getName()));
		}
	}

	static {
		CaptchaAPI.CaptchaSolution = null;
		CaptchaAPI.CaptchaGUITitle = "";
		CaptchaAPI.CaptchaFailCount = new HashMap<Player, Integer>();
		CaptchaAPI.CaptchaSolved = new HashMap<Player, Boolean>();
		CaptchaAPI.RndmItem = new ArrayList<Material>();
	}
}
