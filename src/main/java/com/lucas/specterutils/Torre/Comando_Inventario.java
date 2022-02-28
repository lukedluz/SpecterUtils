package com.lucas.specterutils.Torre;

import com.lucas.specterutils.API.FormatAPI;
import com.lucas.specterutils.API.HeadsAPI;
import com.lucas.specterutils.Main;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.inventory.meta.*;

import org.bukkit.inventory.*;

public class Comando_Inventario implements CommandExecutor {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] a) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		cmd.getName().equals("torre");
		p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
		Inventory inv = Bukkit.createInventory((InventoryHolder) null, 36, "Torre de Cactus");
		ItemStack cabeca = HeadsAPI.cactus;
		SkullMeta cabeca2 = (SkullMeta) cabeca.getItemMeta();
		cabeca2.setDisplayName("§aFarm de Cactus");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add("§fTamanho: §75 andares");
		lore.add("§fCusto:§7 " + FormatAPI.format(Main.m.getConfig().getDouble("Torre_Preço")));
		lore.add("");
		lore.add("§aClique aqui para adquirir uma torre.");
		cabeca2.setLore((List) lore);
		cabeca.setItemMeta((ItemMeta) cabeca2);
		inv.setItem(12, cabeca);

		ItemStack cabeca3 = HeadsAPI.cacto;
		SkullMeta cabeca4 = (SkullMeta) cabeca3.getItemMeta();
		cabeca4.setDisplayName("§aCactus");
		ArrayList<String> lore2 = new ArrayList<String>();
		lore2.add("");
		lore2.add("§7Pre§o: §f1M");
		lore2.add("§7Quantidade: §f16x");
		lore2.add("");
		lore2.add("§eClique para comprar este item!");
		cabeca4.setLore((List) lore2);
		cabeca3.setItemMeta((ItemMeta) cabeca4);
		inv.setItem(14, cabeca3);

		ItemStack n1 = new ItemStack(Material.ARROW, 1, (short) 0);
		ItemMeta n2 = n1.getItemMeta();
		n2.setDisplayName("§cVoltar");
		ArrayList<String> lore3 = new ArrayList<String>();
		lore3.add("§7Clique para voltar");
		n2.setLore((List) lore3);
		n1.setItemMeta(n2);
		inv.setItem(31, n1);
		p.openInventory(inv);
		return true;
	}
}
