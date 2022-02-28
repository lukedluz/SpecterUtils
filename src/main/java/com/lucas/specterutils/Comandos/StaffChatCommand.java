package com.lucas.specterutils.Comandos;

import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
//import org.github.paperspigot.Title;

public class StaffChatCommand implements Listener, CommandExecutor {
	private String append(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < args.length; x++) {
			stringBuilder.append(args[x] + " ");
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			if (!s.hasPermission("specter.staffchat")) {
				Player p = (Player) s;
				//p.sendTitle(new Title("§cSem Permissão", "§fpossua o cargo §e[Ajudante] §fou superior"));
				p.playSound(p.getLocation(), Sound.EXPLODE, 25, 10);
				return false;
			}
		}
		if (args.length == 0) {
			s.sendMessage(
					new String[] { "§c", "§6§lChat Staff§e:", "§eUso correto:", "§a * §6/sc §e<mensagem>", "§c" });
			return false;
		}
		if (args.length > 0) {
			Bukkit.getOnlinePlayers().stream().filter(t -> t.hasPermission("specter.staffchat"))
					.collect(Collectors.toList())
					.forEach(t -> t.sendMessage("§d[SC] " + (s instanceof Player ? ((Player) s).getName() : "Console")
							+ "§f: " + append(args).replace("&", "§")));
			return true;
		}
		return false;
	}
}