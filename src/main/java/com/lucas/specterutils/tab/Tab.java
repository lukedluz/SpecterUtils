package com.lucas.specterutils.tab;

import java.util.HashMap;

import com.lucas.specterutils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Tab {

	public Tab() {
		tab.saveDefaultConfig();
		load();
	}
	public static HubConfig tab = new Config(Main.getInstance(), "tab.yml");
	private static HashMap<String, String> tags = new HashMap<String, String>();

	public static void load(){
		tags.clear();
		for (String s : tab.getConfig().getConfigurationSection("Prefix").getKeys(false)){
			String tag = tab.getString("Prefix." + s + ".Tag").replaceAll("&", "�");
			if (tag.length() > 16){
				tag = tag.substring(0, 16);
			}
			String posicao = tab.getString("Prefix." + s + ".Posicao");
			tags.put(s.toLowerCase(), tag + ":" + posicao);
		}
		update();
	}

	public static void update(){
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Player players : Bukkit.getServer().getOnlinePlayers()){
					setPositions(players);
				}
			}
		}.runTaskTimer(Main.getInstance(), 0L, 5*20L);
	}

	@SuppressWarnings("deprecation")
	public static void setPositions(Player p){
		Scoreboard sb = p.getScoreboard();
		for (Player players : Bukkit.getOnlinePlayers()){
			check(sb, players);
			String grupo = getRank(players);
			if (tags.containsKey(grupo.toLowerCase())){
				if (tags.get(grupo.toLowerCase()).split(":")[0].contains("%c%")){
					if (p.getName().equals(p.getName())){
						String prefix = tags.get(grupo.toLowerCase()).split(":")[0];
						String clan = tags.get(grupo.toLowerCase()).split(":")[1];
						if (clan.length() > 16){
							clan = clan.substring(0, 16);
						}
						if (sb.getTeam(clan) != null){
							sb.getTeam(clan).setSuffix(clan);
							if (!sb.getTeam(clan).getPlayers().contains(players))
								sb.getTeam(clan).addPlayer(players);
						} else {
							Team b = sb.registerNewTeam(clan);
							b.setPrefix(prefix);
							b.setSuffix(clan);
						}
					} else {
						if (sb.getTeam(tags.get(grupo.toLowerCase()).split(":")[1]) != null){
							Team t = sb.getTeam(tags.get(grupo.toLowerCase()).split(":")[1]);
							if (!t.hasPlayer(players)){
								t.addPlayer(players);
							}
						} else {
							sb.getTeam("null").addPlayer(players);
						}
					}
				} else {
					String posicao = tags.get(grupo.toLowerCase()).split(":")[1];
					if (sb.getTeam(posicao) != null){
						Team team = sb.getTeam(posicao);
						if (!team.hasPlayer(players)){
							team.addPlayer(players);
						}
					}
				}
			} else {
				sb.getTeam("null").addPlayer(players);
			}

		}

	}

	private static void check(Scoreboard sb, Player p){
		if (sb.getTeam("null") == null){
			Team t = sb.registerNewTeam("null");
			t.setPrefix("�c?? �7");
		}
		for (String grupos : tags.keySet()){
			String posicao = tags.get(grupos).split(":")[1];
			if (sb.getTeam(posicao) == null){
				Team t = sb.registerNewTeam(posicao);
				String prefix = tags.get(grupos).split(":")[0];
				prefix = prefix.replaceAll("%c%", "");
				t.setPrefix(prefix);
			} else {
				String prefix = tags.get(grupos).split(":")[0];
				prefix = prefix.replaceAll("%c%", "");
				sb.getTeam(posicao).setPrefix(prefix);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static String getRank(Player p){
		PermissionUser pex = PermissionsEx.getUser(p);
		for (String ranks : pex.getGroupNames()){
			return ranks;
		}
		return "default";
	}

}