package net.hubtoggle.commands;

import com.lucas.specterutils.Criar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.hubtoggle.Main;
import net.hubtoggle.object.Toggle;

public class ToggleCommand implements CommandExecutor {

	public static ItemStack getTell(Player p) {
		Toggle t = Main.instance.cache.get(p.getName());
		if (t.isTell()) {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eTell", new String[] { "�7Tell: �aAtivado." }, 13);
		} else {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eTell", new String[] { "�7Tell: �cDesativado." }, 14);
		}
	}
	
	public static ItemStack getCoins(Player p) {
		Toggle t = Main.instance.cache.get(p.getName());
		if (t.isCoins()) {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eCoins", new String[] { "�7Tell: �aAtivado." }, 13);
		} else {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eCoins", new String[] { "�7Tell: �cDesativado." }, 14);
		}
	}
	
	public static ItemStack getTpa(Player p) {
		Toggle t = Main.instance.cache.get(p.getName());
		if (t.isTpa()) {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eTpa", new String[] { "�7Tell: �aAtivado." }, 13);
		} else {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eTpa", new String[] { "�7Tell: �cDesativado." }, 14);
		}
	}

	public static ItemStack getGlobal(Player p) {
		Toggle t = Main.instance.cache.get(p.getName());
		if (t.isGlobal()) {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eGlobal", new String[] { "�7Tell: �aAtivado." }, 13);
		} else {
			return Criar.add(Material.STAINED_GLASS_PANE, "�eGlobal", new String[] { "�7Tell: �cDesativado." }, 14);
		}
	}

	
	public static void openInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, "Prefer�ncias");
		inv.setItem(0, Criar.add(Material.BOOK, "�eMensagem privadas", new String[] {"�fReceba mensagens privadas."}));
		inv.setItem(1, Criar.add(Material.GOLD_NUGGET, "�eReceber coins", new String[] {"�fReceba coins de outros jogadores."}));
		inv.setItem(2, Criar.add(Material.ENDER_PEARL, "�ePedidos de teleporte", new String[] {"�fReceba solicita��es de teleporte."}));
		inv.setItem(3, Criar.add(Material.BOOK_AND_QUILL, "�eVer o chat global", new String[] {"�fVer as mensagens do chat global.", "�7�oVoc� s� consegue ver mensagens de staffers."}));
		inv.setItem(9, getTell(p));
		inv.setItem(10, getCoins(p));
		inv.setItem(11, getTpa(p));
		inv.setItem(12, getGlobal(p));
		
		p.openInventory(inv);
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (cmd.getName().equalsIgnoreCase("toggle")) {
				openInv(p);
			}
		}
		return true;
	}

}
