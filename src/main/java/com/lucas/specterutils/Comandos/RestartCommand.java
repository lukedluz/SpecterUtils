package com.lucas.specterutils.Comandos;

import com.lucas.specterutils.Torre.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartCommand implements CommandExecutor, Listener {
	public static boolean reiniciar;
	public static boolean restarting;

	public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
		if (s instanceof Player) {
			if (!s.hasPermission("specter.reiniciar")) {
				s.sendMessage("§cVocê não possui permissão para fazer isto.");
				return false;
			}
		}
		if (args.length == 0) {
			s.sendMessage("");
			s.sendMessage("§6Comandos disponíveis:");
			s.sendMessage("");
			s.sendMessage("§e/reiniciar auto §f- §7Reiniciar o servidor em 3 minutos.");
			s.sendMessage("§e/reiniciar agora §f- §7Reiniciar o servidor em 30 segundos.");
			s.sendMessage("§e/reiniciar cancelar §f- §7Cancelar o reinicio atual.");
			s.sendMessage("");
			return true;
		}
		if (args[0].equalsIgnoreCase("auto")) {
			reiniciar = true;
			Bukkit.getServer().broadcastMessage("");
			Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando em 3 minutos.");
			Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fAlgumas funções foram desativadas.");
			Bukkit.getServer().broadcastMessage("");
			if (!reiniciar)
				return false;
			new BukkitRunnable() {
				@Override
				public void run() {
					if (reiniciar) {
						Bukkit.getServer().broadcastMessage("");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando em 2 minutos.");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fAlgumas funções foram desativadas.");
						Bukkit.getServer().broadcastMessage("");
					}
				}
			}.runTaskLater(Main.getInstance(), 1200L);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (reiniciar) {
						Bukkit.getServer().broadcastMessage("");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando em 1 minuto.");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fAlgumas funções foram desativadas.");
						Bukkit.getServer().broadcastMessage("");
					}
				}
			}.runTaskLater(Main.getInstance(), 2400L);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (reiniciar) {
						restarting = true;
						for (Player on : Bukkit.getOnlinePlayers()) {
							on.closeInventory();
						}
						Bukkit.getServer().broadcastMessage("");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando em 30 segundos.");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fAlgumas funções foram desativadas.");
						Bukkit.getServer().broadcastMessage("");
					}
				}
			}.runTaskLater(Main.getInstance(), 3000L);
			new BukkitRunnable() {
				@Override
				public void run() {
					if (reiniciar) {
						Bukkit.getServer().savePlayers();
						Bukkit.getServer().broadcastMessage("");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando agora.");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fTodas funções estão sendo desativadas.");
						Bukkit.getServer().broadcastMessage("");
						Main.salvarJogadores();
					}
				}
			}.runTaskLater(Main.getInstance(), 3600L);
		}
		if (args[0].equalsIgnoreCase("agora")) {
			restarting = true;
			Bukkit.getOnlinePlayers().iterator().next().closeInventory();
			reiniciar = true;
			Bukkit.getServer().broadcastMessage("");
			Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando em 30 segundos.");
			Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fAlgumas funções foram desativadas.");
			Bukkit.getServer().broadcastMessage("");
			new BukkitRunnable() {
				@Override
				public void run() {
					if (reiniciar) {
						Bukkit.getServer().savePlayers();
						Bukkit.getServer().broadcastMessage("");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fServidor reiniciando agora.");
						Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §fTodas funções estão sendo desativadas.");
						Bukkit.getServer().broadcastMessage("");
						Main.salvarJogadores();
					}
				}

			}.runTaskLater(Main.getInstance(), 600L);
		}
		if (args[0].equalsIgnoreCase("cancelar")) {
			if (reiniciar) {
				reiniciar = false;
				restarting = false;
				s.sendMessage("§cReinicio cancelado com sucesso!");
				Bukkit.getServer().savePlayers();
				Bukkit.getServer().broadcastMessage("");
				Bukkit.getServer()
						.broadcastMessage(" §e§l[REINICIO] §cO Rein§cio foi cancelado por um membro da equipe..");
				Bukkit.getServer().broadcastMessage(" §e§l[REINICIO] §cTodas funções foram ativadas novamente.");
				Bukkit.getServer().broadcastMessage("");
				return true;
			}
			s.sendMessage("§cNão há um reinicio pendente.");
			return true;
		}
		return false;
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (reiniciar && !e.getPlayer().hasPermission("reiniciar.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cServidor reiniciando, funções desabilitadas.");
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (restarting && !e.getPlayer().hasPermission("reiniciar.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cServidor reiniciando, funções desabilitadas.");
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (restarting && !e.getPlayer().hasPermission("reiniciar.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cServidor reiniciando, funções desabilitadas.");
		}
	}

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		if (restarting && !e.getPlayer().hasPermission("reiniciar.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cServidor reiniciando, funções desabilitadas.");
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (reiniciar && !e.getPlayer().hasPermission("reiniciar.bypass")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cServidor reiniciando, funções desabilitadas.");
		}
	}
}
