package com.lucas.specterutils.Eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lucas.specterutils.API.APIGeral;
import com.lucas.specterutils.API.ActionBar;
import com.lucas.specterutils.API.Cuboid;
import com.lucas.specterutils.API.FormatAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.intellectualcrafters.plot.object.Plot;

import com.lucas.specterutils.Main;
//import com.specter.specterdiamonds.api.DiamondsAPI;

public class MinaPrivada implements Listener {
	static Double q = 0.0;
	static Double x = 0.0;
	static Double d = 0.0;

	protected final boolean percentChance(final double percent) {
		if (percent < 0.0 || percent > 100.0) {
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		}
		final double result = new Random().nextDouble() * 100.0;
		return result <= percent;
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		com.intellectualcrafters.plot.object.Location locp = new com.intellectualcrafters.plot.object.Location(
				e.getBlock().getWorld().getName(), (int) e.getBlock().getLocation().getX(),
				(int) e.getBlock().getLocation().getY(), (int) e.getBlock().getLocation().getZ());
		if (Plot.getPlot(locp) != null) {
			Plot plot = Plot.getPlot(locp);
			if (plot.isOwner(p.getUniqueId()) || plot.isAdded(p.getUniqueId())) {
				if (Main.m.getConfig().getConfigurationSection("MinaPrivada")
						.contains(e.getBlock().getTypeId() + ";" + e.getBlock().getData())) {
					Double i = APIGeral.GetFortuna(p.getItemInHand());
					if (i > 0.0) {
						q = Main.m.getConfig().getDouble(
								"MinaPrivada." + e.getBlock().getTypeId() + ";" + e.getBlock().getData() + ".Money")
								+ (Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
										+ e.getBlock().getData() + ".Money") * i);
						x = Main.m.getConfig()
								.getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";" + e.getBlock().getData()
										+ ".Limite.Valor")
								+ (Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
										+ e.getBlock().getData() + ".Limite.Valor") * i);
						d = Main.m.getConfig()
								.getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";" + e.getBlock().getData()
										+ ".Diamantes.Valor")
								+ (Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
										+ e.getBlock().getData() + ".Diamantes.Valor") * i);
					} else {
						q = Main.m.getConfig().getDouble(
								"MinaPrivada." + e.getBlock().getTypeId() + ";" + e.getBlock().getData() + ".Money");
						x = Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
								+ e.getBlock().getData() + ".Limite.Valor");
						d = Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
								+ e.getBlock().getData() + ".Diamantes.Valor");
					}

					ItemStack Item = p.getItemInHand();
					if (Item.hasItemMeta() && Item.getItemMeta().hasLore()
							&& Item.getItemMeta().getLore().contains("§7Explosivo")) {
						if (percentChance(Main.m.getConfig().getDouble("Explosivo.Chance"))) {
							Location loc1 = e.getBlock().getLocation().add(2.0, 2.0, 2.0);
							Location loc2 = e.getBlock().getLocation().add(-2.0, -2.0, -2.0);
							com.intellectualcrafters.plot.object.Location locp1 = new com.intellectualcrafters.plot.object.Location(
									loc1.getWorld().getName(), (int) loc1.getX(), (int) loc1.getY(), (int) loc1.getZ());
							com.intellectualcrafters.plot.object.Location locp2 = new com.intellectualcrafters.plot.object.Location(
									loc2.getWorld().getName(), (int) loc2.getX(), (int) loc2.getY(), (int) loc2.getZ());
							Cuboid cubo = new Cuboid(loc1, loc2);
							List<Block> it = cubo.getBlocks();
							ArrayList<Block> blocos = new ArrayList<Block>();
							for (Block block : it) {
								if (Plot.getPlot(locp) != null && Plot.getPlot(locp2) != null) {
									Plot plot1 = Plot.getPlot(locp1);
									Plot plot2 = Plot.getPlot(locp2);
									if (!plot1.isOwner(p.getUniqueId()) && !plot1.isAdded(p.getUniqueId())
											|| !plot2.isOwner(p.getUniqueId()) && !plot2.isAdded(p.getUniqueId()))
										return;
									if (block.getType() == e.getBlock().getType()) {
										blocos.add(block);
										block.setType(Material.AIR);
									}
								}
							}
							Main.economy.depositPlayer(p, q * blocos.size());
							ActionBar.sendActionBarMessage(p,
									"§aVocê recebeu §e" + FormatAPI.format(q * blocos.size()) + " Coins");
							blocos.clear();
							return;
						}
					}
					if (percentChance(Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
							+ e.getBlock().getData() + ".Limite.Porcentagem"))) {
						String comando = Main.m
								.getConfig().getString("MinaPrivada." + e.getBlock().getTypeId() + ";"
										+ e.getBlock().getData() + ".Limite.Comando")
								.replace("%p", p.getName()).replace("%q", x.toString());
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
					}
					if (this.percentChance(Main.m.getConfig().getDouble("MinaPrivada." + e.getBlock().getTypeId() + ";"
							+ e.getBlock().getData() + ".Diamantes.Porcentagem"))) {
						//DiamondsAPI.add(p, d);
						Main.economy.depositPlayer(p, q);
						ActionBar.sendActionBarMessage(p, "§aVocê recebeu §b" + FormatAPI.format(d) + " Diamantes");
					} else {
						Main.economy.depositPlayer(p, q);
						ActionBar.sendActionBarMessage(p, "§aVocê recebeu §e" + FormatAPI.format(q) + " Coins");
					}
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					return;
				}
			}
		}
	}

}
