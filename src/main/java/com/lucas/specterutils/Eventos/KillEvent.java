package com.lucas.specterutils.Eventos;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.lucas.specterutils.Main;

public class KillEvent implements Listener {

	@EventHandler
	public void onKill(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player)
			return;

		if (!(e.getEntity().getKiller() instanceof Player))
			return;

		Player p = e.getEntity().getKiller();
		String entidade = e.getEntity().getType().name();
		if (e.getEntity().hasMetadata("atlas_spawners_quantidade")) {
			if (e.getEntity().getMetadata("atlas_spawners_quantidade") != null) {
				if (!e.getEntity().getMetadata("atlas_spawners_quantidade").isEmpty()) {
					if (e.getEntity().getMetadata("atlas_spawners_quantidade").get(0) != null) {
						if (!p.isSneaking()) {
							if (Main.m.getConfig().getConfigurationSection("Mobs").contains(entidade)) {
								List<String> dados = Main.m.getConfig().getStringList("Mobs." + entidade);
								for (String str : dados) {
									String[] split = str.split(";");
									Double percentage = Double.valueOf(split[0]);
									String comando = split[1].replace("%p", p.getName());
									if (percentChance(percentage)) {
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	protected boolean percentChance(double percent) {
		if (percent < 0.0 || percent > 100.0) {
			throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
		}
		double result = new Random().nextDouble() * 100.0;
		return result <= percent;
	}
}
