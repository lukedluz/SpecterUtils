package com.lucas.specterutils.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.lucas.specterutils.Main;

public class Anuncio implements CommandExecutor {

	public ArrayList<String> task = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("anuncio")) {

			if (args.length == 0) {
				p.sendMessage("§c§l[!] §cUtilize: /anuncio <mensagem>");
				return true;
			}

			if (task.contains(p.getName())) {
				p.sendMessage("§c§l[!] §cVocê deve aguardar para usar esse comando novamente!");
				return true;
			}

			String pname = p.getName();

			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.m, new Runnable() {
				public void run() {
					task.remove(pname);
				}
			}, 2400);

			task.add(pname);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				sb.append(args[i]).append(" ");
			}

			String allArgs = sb.toString().trim();

			Bukkit.broadcastMessage("§a§l[Anuncio] §f" + p.getName() + " §7§ §a" + allArgs.replace("&k", "").replace("&m", "").replace("&n", "").replace("&l", "").replace("&o", "").replace("&r", "").replace("&", "§"));
		}
		return true;
	}
}
