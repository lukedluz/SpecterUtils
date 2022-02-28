package com.lucas.specterutils.Torre;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import com.intellectualcrafters.plot.object.Plot;

import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.*;

public class InventarioComprar implements Listener {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@EventHandler
	public void aoclicar12222(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equals("Torre de Cactus")) {
				e.setCancelled(true);
				if (e.getCurrentItem() == null) {
					return;
				}
				if (e.getCurrentItem().getType() == Material.AIR) {
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFarm de Cactus")) {
					/*if (PombaEconomy.getHandler().has(p, Main.m.getConfig().getDouble("Torre_Pre§o"))) {
						PombaEconomy.getHandler().withdrawPlayer(p, Main.m.getConfig().getDouble("Torre_Pre§o"));
						ActionBar.sendActionBarMessage(p, "§aVocê comprou uma §fTorre de Cactus §apor §f"
								+ FormatAPI.format(Main.m.getConfig().getDouble("Torre_Pre§o")) + " §acoins.");
						ItemStack batata = HeadsAPI.cactus;
						SkullMeta batatameta = (SkullMeta) batata.getItemMeta();
						batatameta.setDisplayName("§aFarm de Cactus");
						ArrayList<String> lore = new ArrayList<String>();
						lore.add("§7Ao colocar esse item em sua plot");
						lore.add("§7uma torre de cactus surgir§.");
						lore.add("§7");
						lore.add("§f Tamanho: §78 andares");
						lore.add("§7");
						batatameta.setLore((List) lore);
						batata.setItemMeta((ItemMeta) batatameta);
						p.getInventory().addItem(new ItemStack[] { batata });
					} else {
						ActionBar.sendActionBarMessage(p,
								"§cVocê n\u00e3o tem "
										+ FormatAPI.format(Main.m.getConfig().getDouble("Torre_Pre§o"))
										+ " coins para comprar isso.");
					}
					p.updateInventory();*/
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aCactus")) {
					/*if (PombaEconomy.getHandler().has(p, Main.m.getConfig().getDouble("Cactus_Pre§o"))) {
						PombaEconomy.getHandler().withdrawPlayer(p, Main.m.getConfig().getDouble("Cactus_Pre§o"));
						ActionBar.sendActionBarMessage(p, "§aVocê comprou §f16x Cactus §apor §f"
								+ FormatAPI.format(Main.m.getConfig().getDouble("Cactus_Preço")) + " §acoins.");
						p.getInventory().addItem(new ItemStack(Material.CACTUS, 16));
					} else {
						ActionBar.sendActionBarMessage(p,
								"§cVocê n\u00e3o tem "
										+ FormatAPI.format(Main.m.getConfig().getDouble("Cactus_Pre§o"))
										+ " coins para comprar isso.");
					}*/
					p.updateInventory();
				}
			}
		}
	}

	@EventHandler
	public void aoclicar122sfsdf22(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getTitle().equals("Torre de Cactus")) {
				e.setCancelled(true);
				if (e.getCurrentItem() == null) {
					return;
				}
				if (e.getCurrentItem().getType() == Material.AIR) {
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar")) {
					p.closeInventory();
					p.updateInventory();
					p.chat("/farm");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public static void clicainventario(BlockPlaceEvent bate) {
		Player p = bate.getPlayer();
		if (p.getItemInHand().hasItemMeta()) {
			if (p.getItemInHand().getItemMeta().hasDisplayName()) {
				if (bate.getItemInHand().getItemMeta().getDisplayName().contentEquals("§aFarm de Cactus")) {
					bate.setCancelled(true);

					Location bloco2 = bate.getBlock().getLocation().add(0.0, - 1.0, 0.0);
					Location bloco3 = bate.getBlock().getLocation().add(0.0, - 1.0, 0.0);

					int x = bloco2.getBlockX();
					int y = bloco2.getBlockY();
					int z = bloco2.getBlockZ();

					com.intellectualcrafters.plot.object.Location locp1 = new com.intellectualcrafters.plot.object.Location(
							p.getWorld().getName(), x - 1, y + 31, z - 1);
					com.intellectualcrafters.plot.object.Location locp2 = new com.intellectualcrafters.plot.object.Location(
							p.getWorld().getName(), x + 1, y, z + 1);

					if (Plot.getPlot(locp1) == null || Plot.getPlot(locp2) == null) {
						p.sendMessage("§cVocê deve colocar a torre em uma plot");
						return;
					}

					if (bloco2.add(-1.0, 31.0, -1.0).getBlock().getType() == Material.BEDROCK
							|| bloco2.add(1.0, 0.0, 1.0).getBlock().getType() == Material.BEDROCK) {
						p.sendMessage("§cVocê deve colocar a torre em uma plot");
						return;
					}

					if (bloco2.add(-1.0, 31.0, -1.0).getBlock().getTypeId() == 44
							&& bloco2.add(-1.0, 31.0, -1.0).getBlock().getData() == 1
							|| bloco2.add(1.0, 0.0, 1.0).getBlock().getTypeId() == 44
									&& bloco2.add(1.0, 0.0, 1.0).getBlock().getData() == 1) {
						p.sendMessage("§cVocê deve colocar a torre em uma plot");
						return;
					}

					if (bloco2.add(-1.0, 31.0, -1.0).getY() >= 256) {
						p.sendMessage("§cVocê deve colocar a torre abaixo da camada 256");
						return;
					}

					Plot plot = Plot.getPlot(locp1);
					Plot plot2 = Plot.getPlot(locp1);
					if (plot.isOwner(p.getUniqueId()) && plot2.isOwner(p.getUniqueId())) {
						p.playEffect(bloco3.add(0.4, 1.0, 0.4), Effect.HAPPY_VILLAGER, 100);
						bloco3.add(0.0, 0.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, 0.0, 2.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(-1.0, 0.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(2.0, 0.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(-1.0, 3.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, 0.0, 2.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(-1.0, 0.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.5, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(2.5, 0.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, -1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(-1.0, 3.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -2.0, 2.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, -2.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(-2.0, -2.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, 1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(-0.5, 1.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -2.0, 2.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, -2.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(-2.0, -2.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, 1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.5, 1.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(0.0, -2.0, 2.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, -2.0, -1.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(-2.0, -2.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.SAND);
						bloco3.add(0.0, 1.0, 0.0).getBlock().setType(Material.CACTUS);
						bloco3.add(1.0, 1.0, 0.0).getBlock().setType(Material.COBBLESTONE);
						ItemStack hand2 = p.getInventory().getItemInHand();
						hand2.setAmount(hand2.getAmount() - 1);
						p.getInventory().setItemInHand(hand2);
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
						p.updateInventory();
					}
				}
			}
		}
	}
}
