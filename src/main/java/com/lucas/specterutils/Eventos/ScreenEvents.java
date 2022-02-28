package com.lucas.specterutils.Eventos;

import java.util.stream.Collectors;

import com.lucas.specterutils.Comandos.ScreenShare;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ScreenEvents implements Listener {
	public static String ss = "�6�lSS ";

	@EventHandler
	public void blockCommand(PlayerTeleportEvent e) {
		if (ScreenShare.jogadoresSS.contains(e.getPlayer())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void blockCommand(PlayerCommandPreprocessEvent e) {
		if (ScreenShare.jogadoresSS.contains(e.getPlayer())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ss + "�cComando Bloqueado durante SS");

		} else {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void quit(PlayerQuitEvent e) {
		if (ScreenShare.jogadoresSS.contains(e.getPlayer())) {
			Bukkit.getOnlinePlayers().stream().filter(t -> t.hasPermission("pomba.staffchat"))
					.collect(Collectors.toList()).forEach(a -> a.sendMessage(ss + "�a" + e.getPlayer().getName()
							+ " �7deslogou do servidor enquanto estava em �aSS�7!"));			
			ScreenShare.jogadoresSS.remove(e.getPlayer());
		}

	}
}
