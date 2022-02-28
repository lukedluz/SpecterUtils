package com.lucas.specterutils.Torre;

import com.lucas.specterutils.Comandos.LaggCommand;
import com.lucas.specterutils.Comandos.LobbyCommand;
import com.lucas.specterutils.Comandos.RepararCommand;
import com.lucas.specterutils.Comandos.RestartCommand;
import com.lucas.specterutils.Comandos.ScreenShare;
import com.lucas.specterutils.Comandos.StaffChatCommand;
import com.lucas.specterutils.Eventos.ScreenEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Main extends JavaPlugin implements Listener {
	private static Main plugin;

	public static Main getInstance() {
		return plugin;
	}

	public static String mensagem, avisos;
	public Scoreboard staff, jogador;
	public Location arena;

	public void onEnable() {
		plugin = this;
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");		
		saveDefaultConfig();
		mensagem = getConfig().getString("Mensagens.Limpo");
		avisos = getConfig().getString("Mensagens.Avisos");
		getCommand("sc").setExecutor(new StaffChatCommand());
		getCommand("ss").setExecutor(new ScreenShare());
		getCommand("lobby").setExecutor(new LobbyCommand());
		getCommand("reparar").setExecutor(new RepararCommand());
		getCommand("reiniciar").setExecutor(new RestartCommand());
		getCommand("lagg").setExecutor(new LaggCommand());
		Bukkit.getPluginManager().registerEvents(new ScreenEvents(), this);
		buildScoreboards();
		createTask();
		arena = getLocation(getConfig(), "ScreenShare");
	}

	private Location getLocation(FileConfiguration f, String path) {
		World world = Bukkit.getWorld(f.getString(path + ".Mundo"));
		double x = f.getDouble(path + ".X");
		double y = f.getDouble(path + ".Y");
		double z = f.getDouble(path + ".Z");
		float yaw = (float) f.getDouble(path + ".Yaw");
		float pitch = (float) f.getDouble(path + ".Pitch");
		return new Location(world, x, y, z, yaw, pitch);
	}

	private void createTask() {
		int time = 20 * 60 * 3;
		new BukkitRunnable() {
			int count = time;

			@Override
			public void run() {
				count -= 10;
				if (count == 20) {
					//ActionBar ac = new ActionBar(avisos.replaceAll("@time", count + "").replaceAll("&", "§"));
					//ac.sendToAll();
					return;
				}
				if (count == 10) {
					//ActionBar ac = new ActionBar(avisos.replaceAll("@time", count + "").replaceAll("&", "§"));
					//ac.sendToAll();
					return;
				}
				if (count <= 0) {
					count = time;
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
					//ActionBar ac = new ActionBar(mensagem.replace("@itens", o + "").replace("&", "§"));
					//ac.sendToAll();
				}
			}
		}.runTaskTimerAsynchronously(this, 0, 10 * 20L);
	}

	public static void enviarPlayer(Player player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(server);
		player.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}

	public static void salvarJogadores() {
		for (Player on : Bukkit.getOnlinePlayers()) {
			on.closeInventory();
			on.saveData();
			try {
				enviarPlayer(on, "lobby");
			} catch (Exception e) {
				on.kickPlayer("§4§lReinício Automático\n\n   §cServidor reiniciando.");
			}
		}
		for (World world : Bukkit.getServer().getWorlds()) {
			world.save();
		}
		Bukkit.getServer().shutdown();
	}

	@SuppressWarnings("deprecation")
	private void buildScoreboards() {
		staff = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective s = staff.registerNewObjective("teste2", "dummy");
		s.setDisplaySlot(DisplaySlot.SIDEBAR);
		s.setDisplayName("§6§lScreen§e§lShare");
		s.getScore(Bukkit.getOfflinePlayer("§f")).setScore(11);
		s.getScore(Bukkit.getOfflinePlayer("§eVocê puxou um jogador para SS")).setScore(10);
		s.getScore(Bukkit.getOfflinePlayer("§e")).setScore(9);
		s.getScore(Bukkit.getOfflinePlayer("§fFaça os devidos procedimentos, e")).setScore(8);
		s.getScore(Bukkit.getOfflinePlayer("§fnão saia sem concluir")).setScore(7);
		s.getScore(Bukkit.getOfflinePlayer("§fa §6screenshare")).setScore(6);
		s.getScore(Bukkit.getOfflinePlayer("§e")).setScore(5);
		s.getScore(Bukkit.getOfflinePlayer("§fApós liberar o player,")).setScore(4);
		s.getScore(Bukkit.getOfflinePlayer("§fvolte a moderar normalmente")).setScore(3);
		s.getScore(Bukkit.getOfflinePlayer("§f")).setScore(2);
		s.getScore(Bukkit.getOfflinePlayer("§bredespecter.xyz")).setScore(1);
		jogador = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = jogador.registerNewObjective("teste", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§6§lScreen§e§lShare");
		o.getScore(Bukkit.getOfflinePlayer("§f§l")).setScore(11);
		o.getScore(Bukkit.getOfflinePlayer("§eVocê foi puxado para SS")).setScore(10);
		o.getScore(Bukkit.getOfflinePlayer("§e")).setScore(9);
		o.getScore(Bukkit.getOfflinePlayer("§fSiga todos os procedimentos")).setScore(8);
		o.getScore(Bukkit.getOfflinePlayer("§fque o staff pedir")).setScore(7);
		o.getScore(Bukkit.getOfflinePlayer("§fou será punido.")).setScore(6);
		o.getScore(Bukkit.getOfflinePlayer("§f")).setScore(5);
		o.getScore(Bukkit.getOfflinePlayer("§cSe deslogar")).setScore(4);
		o.getScore(Bukkit.getOfflinePlayer("§cvocê será banido!")).setScore(3);
		o.getScore(Bukkit.getOfflinePlayer("§e")).setScore(2);
		o.getScore(Bukkit.getOfflinePlayer("§bredespecter.xyz")).setScore(1);
	}
}
