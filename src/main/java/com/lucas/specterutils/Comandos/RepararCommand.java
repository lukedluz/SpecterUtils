package com.lucas.specterutils.Comandos;

import java.util.Arrays;

import com.lucas.specterutils.API.ManageItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepararCommand implements CommandExecutor {
	public Material[] tools = new Material[] { Material.DIAMOND_PICKAXE, Material.DIAMOND_SWORD, Material.DIAMOND_SPADE,
			Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE,
			Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.IRON_PICKAXE, Material.IRON_SWORD,
			Material.IRON_SPADE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_HELMET, Material.IRON_CHESTPLATE,
			Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_PICKAXE, Material.GOLD_SWORD,
			Material.GOLD_SPADE, Material.GOLD_AXE, Material.GOLD_HOE, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE,
			Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.STONE_PICKAXE, Material.STONE_SWORD,
			Material.STONE_SPADE, Material.STONE_AXE, Material.STONE_HOE, Material.CHAINMAIL_HELMET,
			Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.WOOD_PICKAXE,
			Material.WOOD_SWORD, Material.WOOD_SPADE, Material.WOOD_AXE, Material.WOOD_HOE, Material.LEATHER_HELMET,
			Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.BOW };

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (!(s instanceof Player))
			return false;
		Player p = (Player) s;
		if (p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR) {
			p.sendMessage("§cVocê precisa estar segurando um item.");
			return false;
		}
		ItemStack item = p.getItemInHand();
		Material m = item.getType();
		if (!Arrays.asList(tools).contains(m)) {
			p.sendMessage("§cEste item não pode ser reparado.");
			return false;
		}
		if (item.getDurability() == 0) {
			p.sendMessage("§cEste item não está danificado.");
			p.playSound(p.getLocation(), Sound.NOTE_BASS, 10, 1);
			return false;
		}
		Material type = ct(m);
		if (!ManageItems.test(p, 10, type)) {
			p.sendMessage("§cVocê precisa de 10 " + translate(type) + " para reparar este item.");
			return false;
		}
		ManageItems.consumeItem(p, 10, type);
		item.setDurability((short) 0);
		p.sendMessage("§aItem reparado com sucesso.");
		p.updateInventory();
		p.playSound(p.getLocation(), Sound.ANVIL_USE, 10, 1);
		return true;
	}

	private String translate(Material type) {
		switch (type) {
		case DIAMOND:
			return "Diamantes";
		case IRON_INGOT:
			return "Barras de Ferro";
		case GOLD_INGOT:
			return "Barras de Ouro";
		case COBBLESTONE:
			return "Cobblestones";
		case LEATHER:
			return "Couros";
		case WOOD:
			return "Madeiras";
		case STRING:
			return "Linhas";
		default:
			return "Error";
		}
	}

	private Material ct(Material m) {
		String t = m.toString().toLowerCase();
		if (t.contains("diamond"))
			return Material.DIAMOND;
		if (t.contains("iron"))
			return Material.IRON_INGOT;
		if (t.contains("gold"))
			return Material.GOLD_INGOT;
		if (t.contains("stone"))
			return Material.COBBLESTONE;
		if (t.contains("leather") || t.contains("chainmail"))
			return Material.LEATHER;
		if (t.contains("bow"))
			return Material.STRING;
		if (t.contains("wood"))
			return Material.WOOD;
		return m;
	}
}
