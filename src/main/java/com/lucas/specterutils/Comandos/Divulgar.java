package com.lucas.specterutils.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.lucas.specterutils.Main;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Divulgar implements CommandExecutor {
	
	public ArrayList<String> task = new ArrayList<String>();
	
	@SuppressWarnings("deprecation")
	public static String getTag(String p) {
		if (PermissionsEx.getUser(p).getGroups()[0].getPrefix().replaceAll("&", "§").equalsIgnoreCase("")) {
			return "§7[M] ";
		} else {
			return PermissionsEx.getUser(p).getGroups()[0].getPrefix().replaceAll("&", "§");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("divulgar")) {
			
			if (args.length == 0) {
				p.sendMessage("§c§l[!] §cUtilize: /divulgar <mensagem>");
				return true;
			}
			if (!p.hasPermission("pomba.yt")) {
				p.sendMessage("§c§l[!] §cVocê não tem permissão para utilizar esse comando!");
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
			}, 60 * 1200);
			
			task.add(pname);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				sb.append(args[i]).append(" ");
			}

			String allArgs = sb.toString().trim();
			
			if (!allArgs.contains("https://") && !allArgs.contains("http://")) {
				p.sendMessage("§c§l[!] §cVoc}e deve divulgar um link válido!");
				return true;
			}
			
			if (!allArgs.contains("youtube") && !allArgs.contains("youtu.be") && !allArgs.contains("twitch")) {
				p.sendMessage("§c§l[!] §cVocê deve divulgar um link válido!");
				return true;
			}
			
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§a§l[Divulgação] " + getTag(p.getName()) + p.getName() + " §7§ §a" + allArgs.replace("&k", "").replace("&m", "").replace("&n", "").replace("&l", "").replace("&o", "").replace("&r", "").replace("&", "§"));
			Bukkit.broadcastMessage(" ");
		}
		return true;
	}
}
