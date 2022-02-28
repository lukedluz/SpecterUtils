package com.lucas.specterutils.Eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventosMenu implements Listener {

	@EventHandler
	public void onclick(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("Eventos")) {
			e.setCancelled(true);
			if (e.getInventory().getSize() == 54) {
				if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
					if (e.getCurrentItem().getType() == Material.ARROW) {
						e.getWhoClicked().closeInventory();
						((Player) e.getWhoClicked()).chat("/eventos");
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onOpen(InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		if (e.getInventory().getName().equalsIgnoreCase("Eventos")) {
			if (e.getInventory().getSize() == 36) {
				if (e.getInventory().getItem(11) != null
						&& e.getInventory().getItem(11).getType() == Material.GOLD_NUGGET) {
					
					ItemStack bolao = e.getInventory().getItem(11);
					ItemMeta bolaometa = e.getInventory().getItem(11).getItemMeta();
					bolao.setItemMeta(bolaometa);
					
					ItemStack chat = e.getInventory().getItem(12);
					ItemMeta chatmeta = e.getInventory().getItem(12).getItemMeta();
					chat.setItemMeta(chatmeta);
					
					ItemStack fastclick = e.getInventory().getItem(13);
					ItemMeta fastclickmeta = e.getInventory().getItem(13).getItemMeta();
					fastclick.setItemMeta(fastclickmeta);
					
					ItemStack loteria = e.getInventory().getItem(14);
					ItemMeta loteriameta = e.getInventory().getItem(14).getItemMeta();
					loteria.setItemMeta(loteriameta);
					
					ItemStack matematica = e.getInventory().getItem(15);
					ItemMeta matematicameta = e.getInventory().getItem(15).getItemMeta();
					matematica.setItemMeta(matematicameta);
					
					ItemStack back = e.getInventory().getItem(31);
					ItemMeta backmeta = e.getInventory().getItem(31).getItemMeta();
					back.setItemMeta(backmeta);
					
					ItemStack booster = new ItemStack(Material.EXP_BOTTLE);
					ItemMeta boostermeta = booster.getItemMeta();
					boostermeta.setDisplayName("�aBooster de blocos 2x");
					ArrayList<String> boosterlore = new ArrayList<String>();
					boosterlore.add("�7");
					boosterlore.add("�7 Hor�rios");
					boosterlore.add("  �fSegunda-feira �s 23:00");
					boosterlore.add("  �fTer�a-feira �s 23:00");
					boosterlore.add("  �fQuarta-feira �s 23:00");
					boosterlore.add("  �fQuinta-feira �s 23:00");
					boosterlore.add("  �fSexta-feira �s 23:00");
					boosterlore.add("  �fS�bado �s 23:00");
					boosterlore.add("  �fDomingo �s 23:00");
					boostermeta.setLore(boosterlore);
					booster.setItemMeta(boostermeta);
					
					ItemStack killer = new ItemStack(Material.DIAMOND_SWORD);
					ItemMeta killermeta = killer.getItemMeta();
					killermeta.setDisplayName("�aKiller");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("�7");
					lore.add("�7 Hor�rios");
					lore.add("  �fS�bado �s 17:00");
					killermeta.setLore(lore);
					killer.setItemMeta(killermeta);
					
					ItemStack gladiador = new ItemStack(Material.IRON_FENCE);
					ItemMeta gladiadormeta = gladiador.getItemMeta();
					gladiadormeta.setDisplayName("�aGladiador");
					ArrayList<String> loreg = new ArrayList<String>();
					loreg.add("�7");
					loreg.add("�7 Hor�rios");
					loreg.add("  �fDomingo �s 17:00");
					gladiadormeta.setLore(loreg);
					gladiador.setItemMeta(gladiadormeta);
					
					ItemStack mina = new ItemStack(Material.DIAMOND_BLOCK);
					ItemMeta minameta = mina.getItemMeta();
					minameta.setDisplayName("�aMina recheada");
					ArrayList<String> minalore = new ArrayList<String>();
					minalore.add("�7");
					minalore.add("�7 Hor�rios");
					minalore.add("  �fSegunda-feira �s 15:00");
					minalore.add("  �fTer�a-feira �s 15:00");
					minalore.add("  �fQuarta-feira �s 15:00");
					minalore.add("  �fQuinta-feira �s 15:00");
					minalore.add("  �fSexta-feira �s 15:00");
					minalore.add("  �fS�bado �s 15:00");
					minalore.add("  �fDomingo �s 15:00");
					minameta.setLore(minalore);
					mina.setItemMeta(minameta);
					
					ItemStack Fight = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
					ItemMeta Fightmeta = Fight.getItemMeta();
					Fightmeta.setDisplayName("�aFight");
					ArrayList<String> Fightlore = new ArrayList<String>();
					Fightlore.add("�7");
					Fightlore.add("�7 Hor�rios");
					Fightlore.add("  �fSegunda-feira �s 14:00");
					Fightlore.add("  �fTer�a-feira �s 14:00");
					Fightlore.add("  �fQuarta-feira �s 14:00");
					Fightlore.add("  �fQuinta-feira �s 14:00");
					Fightlore.add("  �fSexta-feira �s 14:00");
					Fightlore.add("  �fS�bado �s 14:00");
					Fightlore.add("  �fDomingo �s 14:00");
					Fightmeta.setLore(Fightlore);
					Fight.setItemMeta(Fightmeta);
					
					ItemStack Paintball = new ItemStack(Material.BOW);
					ItemMeta Paintballmeta = Paintball.getItemMeta();
					Paintballmeta.setDisplayName("�aPaintball");
					ArrayList<String> Paintballlore = new ArrayList<String>();
					Paintballlore.add("�7");
					Paintballlore.add("�7 Hor�rios");
					Paintballlore.add("  �fSegunda-feira � 12:00");
					Paintballlore.add("  �fTer�a-feira � 12:00");
					Paintballlore.add("  �fQuarta-feira � 12:00");
					Paintballlore.add("  �fQuinta-feira � 12:00");
					Paintballlore.add("  �fSexta-feira � 12:00");
					Paintballlore.add("  �fS�bado � 12:00");
					Paintballlore.add("  �fDomingo � 12:00");
					Paintballmeta.setLore(Paintballlore);
					Paintball.setItemMeta(Paintballmeta);
					
					ItemStack Batataquente = new ItemStack(Material.BAKED_POTATO);
					ItemMeta Batataquentemeta = Batataquente.getItemMeta();
					Batataquentemeta.setDisplayName("�aBatata Quente");
					ArrayList<String> Batataquentelore = new ArrayList<String>();
					Batataquentelore.add("�7");
					Batataquentelore.add("�7 Hor�rios");
					Batataquentelore.add("  �fSegunda-feira �s 13:00");
					Batataquentelore.add("  �fTer�a-feira �s 13:00");
					Batataquentelore.add("  �fQuarta-feira �s 13:00");
					Batataquentelore.add("  �fQuinta-feira �s 13:00");
					Batataquentelore.add("  �fSexta-feira �s 13:00");
					Batataquentelore.add("  �fS�bado �s 13:00");
					Batataquentelore.add("  �fDomingo �s 13:00");
					Batataquentemeta.setLore(Batataquentelore);
					Batataquente.setItemMeta(Batataquentemeta);
					
					ItemStack Semaforo = new ItemStack(Material.DOUBLE_PLANT);
					ItemMeta Semaforometa = Semaforo.getItemMeta();
					Semaforometa.setDisplayName("�aSem�foro");
					ArrayList<String> Semaforolore = new ArrayList<String>();
					Semaforolore.add("�7");
					Semaforolore.add("�7 Hor�rios");
					Semaforolore.add("  �fSegunda-feira �s 16:00");
					Semaforolore.add("  �fTer�a-feira �s 16:00");
					Semaforolore.add("  �fQuarta-feira �s 16:00");
					Semaforolore.add("  �fQuinta-feira �s 16:00");
					Semaforolore.add("  �fSexta-feira �s 16:00");
					Semaforolore.add("  �fS�bado �s 16:00");
					Semaforolore.add("  �fDomingo �s 16:00");
					Semaforometa.setLore(Semaforolore);
					Semaforo.setItemMeta(Semaforometa);
					
					ItemStack spleef = new ItemStack(Material.DIAMOND_SPADE);
					ItemMeta spleefmeta = spleef.getItemMeta();
					spleefmeta.setDisplayName("�aSpleef");
					ArrayList<String> spleeflore = new ArrayList<String>();
					spleeflore.add("�7");
					spleeflore.add("�7 Hor�rios");
					spleeflore.add("  �fSegunda-feira �s 18:00");
					spleeflore.add("  �fTer�a-feira �s 18:00");
					spleeflore.add("  �fQuarta-feira �s 18:00");
					spleeflore.add("  �fQuinta-feira �s 18:00");
					spleeflore.add("  �fSexta-feira �s 18:00");
					spleeflore.add("  �fS�bado �s 18:00");
					spleeflore.add("  �fDomingo �s 18:00");
					spleefmeta.setLore(spleeflore);
					spleef.setItemMeta(spleefmeta);
					
					e.setCancelled(true);
					Inventory eventos = Bukkit.createInventory(null, 54, "Eventos");
					
					eventos.setItem(10, bolao);
					eventos.setItem(11, chat);
					eventos.setItem(12, fastclick);
					eventos.setItem(13, loteria);
					eventos.setItem(14, Paintball);
					eventos.setItem(15, Batataquente);
					eventos.setItem(16, matematica);
					eventos.setItem(20, Fight);
					eventos.setItem(21, mina);
					eventos.setItem(22, Semaforo);
					eventos.setItem(23, killer);
					eventos.setItem(24, gladiador);
					eventos.setItem(30, spleef);
					eventos.setItem(32, booster);
					eventos.setItem(49, back);
					
					p.openInventory(eventos);
				}
			}
		}
	}
}
