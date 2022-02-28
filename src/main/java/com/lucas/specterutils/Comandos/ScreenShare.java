package com.lucas.specterutils.Comandos;

import java.util.ArrayList;

import com.lucas.specterutils.Torre.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ScreenShare implements Listener, CommandExecutor {
	public static String ss = "§6[§eSS§6] ";
	public static ScreenShare instance;
	public static ArrayList<Player> jogadoresSS = new ArrayList<>();
	public static ArrayList<Player> StaffSS = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof ConsoleCommandSender) {
			s.sendMessage("§cEste comando está disponível apenas pelo servidor");
			return false;
		}
		Player p = (Player) s;
		if (!p.hasPermission("pomba.ss")) {
			//p.sendTitle(new Title("§cSem Permissão", "§fpossua o cargo §2[Moderador] §fou superior"));
			p.playSound(p.getLocation(), Sound.CAT_MEOW, 2L, 1L);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage(new String[] { "§7", "§e§lScreen§6§lShare§e:", "§7",
					"§a* §e/ss telar §6<nick> §aPuxar um jogador para SS",
					"§a* §e/ss liberar §6<nick> §aLiberar um jogador da SS", "§a* §e/ss arena §aIr até a Arena SS",
					"§a* §e/ss setar §aSetar a arena SS", "§7" });
			return false;
		}
		if (args[0].equalsIgnoreCase("liberar")) {
			if (args.length == 1) {
				p.sendMessage(ss + "§cUse /ss liberar <jogador> para liberar um jogador da §eSS§a.");
				return true;
			}
			Player alvo = Bukkit.getPlayer(args[1]);
			if (alvo == null) {
				p.sendMessage(ss + "§cO jogador " + args[1] + " §cestá offline.");
				return true;
			}
			if (!jogadoresSS.contains(alvo)) {
				p.sendMessage(ss + "§aEsse jogador não está em §eSS§c.");
				return true;
			}
			if (!StaffSS.contains(p)) {
				p.sendMessage(ss + "§aVocê não está em §eSS§c.");
				return true;
			}
			Bukkit.broadcastMessage(ss + "§2" + alvo.getName() + " §afoi liberado da §eSS§a!");
			jogadoresSS.remove(alvo);
			StaffSS.remove(p);
			alvo.kickPlayer("§e§lScreen§6§lShare\n\n§aVocê foi liberado da ScreenShare.");
			p.kickPlayer("§e§lScreen§6§lShare\n\n§aVocê liberou o §2" + alvo.getName() + "§a da §eSS§a.");
			return true;
		}
		if (args[0].equalsIgnoreCase("setar")) {
			if (p.hasPermission("specter.ssadmin")) {
				setLocation(Main.getInstance().getConfig(), "ScreenShare", p.getLocation());
				Main.getInstance().saveConfig();
				Main.getInstance().arena = p.getLocation();
				p.sendMessage("§aSS Setada para as coordenadas" + " §e(§6X§e:" + p.getLocation().getBlockX() + " §6Y§e:"
						+ p.getLocation().getBlockY() + " §6Z§e:" + p.getLocation().getBlockZ() + "§e)");
				return false;
			}
			//p.sendTitle(new Title("§cSem Permissão", "§fpossua o cargo §4[Gerente] §fou superior"));
			p.playSound(p.getLocation(), Sound.CAT_MEOW, 2L, 1L);
			return true;
		}
		if (args[0].equalsIgnoreCase("arena")) {
			if (Main.getInstance().arena != null) {
				p.teleport(Main.getInstance().arena);
				p.playSound(p.getLocation(), Sound.CLICK, 2L, 1L);
				//p.sendTitle(new Title("§e§lScreen§6§lShare", "§fteleportado §epara §fa arena §6SS"));
				return true;
			}
			p.sendMessage("§cA SS n§o foi setada!");
			return false;
		}
		if (args[0].equalsIgnoreCase("telar")) {
			if (args.length == 1) {
				p.sendMessage(ss + "§cUse /ss telar <jogador> para telar um jogador.");
				return true;
			}
			Player alvo = Bukkit.getPlayer(args[1]);
			if (alvo == null) {
				p.sendMessage(ss + "§cJogador não encontrado.");
				return true;
			}
			if (jogadoresSS.contains(alvo)) {
				p.sendMessage(ss + "§cEste jogador já está sendo telado por outro staffer.");
				return true;
			}
			if (alvo == p) {
				p.sendMessage("§cVoc§ n§o pode telar a si mesmo!");
				return true;
			}
			jogadoresSS.add(alvo);
			StaffSS.add(p);
			p.sendMessage(ss + "§eScreenShare Iniciada!");
			p.setScoreboard(Main.getInstance().staff);
			alvo.setScoreboard(Main.getInstance().jogador);
			alvo.sendMessage(ss + "§cBAIXE: &7anydesk.pt/download &a(aplicativo usado para telar)§7.");
			alvo.sendMessage(
					ss + "§7Você foi §epuxado §7em §e§lScreen§6§lShare, §7faça tudo que o §estaffer §7mandar!");
			alvo.playSound(alvo.getLocation(), Sound.EXPLODE, 1L, 1L);
			p.teleport(Main.getInstance().arena);
			alvo.teleport(Main.getInstance().arena);
			alvo.playSound(p.getLocation(), Sound.CLICK, 2L, 1L);
			p.playSound(p.getLocation(), Sound.CLICK, 2L, 1L);
			return true;
		}
		p.sendMessage(new String[] { "§7", "§e§lScreen§6§lShare§e:", "§7",
				"§a* §e/ss telar §6<nick> §aPuxar um jogador para SS",
				"§a* §e/ss liberar §6<nick> §aLiberar um jogador da SS", "§a* §e/ss arena §aIr até a Arena SS",
				"§a* §e/ss setar §aSetar a arena SS", "§7" });
		return false;

	}

	public static void setLocation(FileConfiguration f, String path, Location location) {
		f.set(path + ".Mundo", location.getWorld().getName());
		f.set(path + ".X", location.getX());
		f.set(path + ".Y", location.getY());
		f.set(path + ".Z", location.getZ());
		f.set(path + ".Yaw", location.getYaw());
		f.set(path + ".Pitch", location.getPitch());
		Main.getInstance().saveConfig();
	}
}
