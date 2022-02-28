package com.lucas.specterutils.Comandos;

import java.lang.reflect.Field;
import java.util.Map;

import com.lucas.specterutils.Torre.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.scheduler.BukkitRunnable;

public class LaggCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			if (!p.hasPermission("laggclear.admin")) {
				p.sendMessage("§7Comando invalido.");
				return true;
			}
		}
		if (args.length == 0) {
			s.sendMessage("§bSPECTER LAGG - Comandos");
			s.sendMessage("§7/" + l + " metadata - Checar registros na metadata");
			s.sendMessage("§7/" + l + " check - Checar número de entidades");
			s.sendMessage("§7/" + l + " clear - Limpar o chão e executar todas as tarefas abaixo");
			s.sendMessage("§7/" + l + " killmobs - Matar todos os mobs ativos");
			s.sendMessage("§7/" + l + " unloadchunks - Descarregar os chunks");
			return true;
		} else {
			if (args[0].equalsIgnoreCase("unloadchunks")) {
				int i = 0;
				for (World world : Bukkit.getWorlds()) {
					for (Chunk chunk : world.getLoadedChunks()) {
						if (chunk.unload(true, true)) {
							i++;
						}
					}
				}
				s.sendMessage("§eForam descarregados " + i + " chunks.");
				return true;
			}
			if (args[0].equalsIgnoreCase("metadata")) {
				sendMetadataStoreInfo(s);
				return true;
			}
			if (args[0].equalsIgnoreCase("gc")) {
				new BukkitRunnable() {

					@Override
					public void run() {
						System.gc();
						s.sendMessage("§aGarbage Collector enviado com sucesso.");

					}
				}.runTaskAsynchronously(Main.getInstance());
				return true;
			}
			if (args[0].equalsIgnoreCase("clear")) {
				int o = 0;
				for (World w : Bukkit.getWorlds()) {
					for (Entity i : w.getEntities()) {
						if (i instanceof Item && !i.isDead()) {
							i.remove();
							o++;
						}
						if (i instanceof Arrow) {
							i.remove();
							o++;
						}
					}
				}
				//ActionBar ac = new ActionBar(Main.mensagem.replace("@itens", o + "").replace("&", "§"));
				//ac.sendToAll();
				int i = 0;
				for (World world : Bukkit.getWorlds()) {
					for (Entity en : world.getEntities()) {
						if (en instanceof Animals || en instanceof Creature) {
							if (!en.isDead()) {
								en.remove();
								i++;
							}
						}
					}
				}
				s.sendMessage("§eForam descarregados " + i + " mobs.");
				new BukkitRunnable() {

					@Override
					public void run() {
						System.gc();
						s.sendMessage("§aGarbage Collector enviado com sucesso.");

					}
				}.runTaskAsynchronously(Main.getInstance());
				int ifg = 0;
				for (World world : Bukkit.getWorlds()) {
					for (Chunk chunk : world.getLoadedChunks()) {
						if (chunk.unload(true, true)) {
							ifg++;
						}
					}
				}
				s.sendMessage("§eForam descarregados " + ifg + " chunks.");
				return true;
			}
			if (args[0].equalsIgnoreCase("check")) {
				int i = 0;
				for (World world : Bukkit.getWorlds()) {
					for (Entity en : world.getEntities()) {
						if (en instanceof Animals || en instanceof Creature) {
							if (!en.isDead()) {
								en.remove();
								i++;
							}
						}
					}
				}
				s.sendMessage("§eTotal de entidades: §f" + i);
				return true;
			}
			if (args[0].equalsIgnoreCase("killmobs")) {
				int i = 0;
				for (World world : Bukkit.getWorlds()) {
					for (Entity en : world.getEntities()) {
						if (en instanceof Animals || en instanceof Creature) {
							if (!en.isDead()) {
								en.remove();
								i++;
							}
						}
					}
				}
				s.sendMessage("§eForam descarregados " + i + " mobs.");
				return true;
			}
			s.sendMessage("§6POMBA LAGG - Comandos");
			s.sendMessage("§7/" + l + " unloadchunks - Descarregar os chunks");
			s.sendMessage("§7/" + l + " killmobs - Matar todos os mobs ativos");
			s.sendMessage("§7/" + l + " metadata - Checar registros na metadata");
			s.sendMessage("§7/" + l + " check - Checar n§mero de entidades");
		}
		return false;
	}

	// SPIGOT FORUM
	private void sendMetadataStoreInfo(CommandSender sender) {
		// Get the entity map via reflection magic
		CraftServer craftServer = (CraftServer) Main.getInstance().getServer();
		Map<?, ?> metadataMap;

		try {
			Field f = MetadataStoreBase.class.getDeclaredField("metadataMap");
			f.setAccessible(true);
			metadataMap = (Map<?, ?>) f.get(craftServer.getEntityMetadata());
		} catch (ReflectiveOperationException ex) {
			sender.sendMessage("Error while reflecting: " + ex.getMessage());
			sender.sendMessage("See console for details.");
			ex.printStackTrace();
			return;
		}

		// Spit it out
		sender.sendMessage("§eObjects na metadata: §f" + metadataMap.size());
	}

}
