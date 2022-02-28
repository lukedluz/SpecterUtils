package net.hubtoggle.object;

import java.util.List;
import java.util.stream.Collectors;

import net.hubtoggle.Main;

public class Toggle {

	private String jogador;
	private boolean tell;
	private boolean coins;
	private boolean tpa;
	private boolean global;
	
	public Toggle(String jogador, boolean tell, boolean coins, boolean tpa, boolean global) {
		this.jogador = jogador;
		this.tell = tell;
		this.coins = coins;
		this.tpa = tpa;
		this.global = global;
	}
	public String getJogador() {
		return jogador;
	}
	public void setJogador(String jogador) {
		this.jogador = jogador;
	}
	public boolean isTell() {
		return tell;
	}
	public void setTell(boolean tell) {
		this.tell = tell;
	}
	public boolean isCoins() {
		return coins;
	}
	public void setCoins(boolean coins) {
		this.coins = coins;
	}
	public boolean isTpa() {
		return tpa;
	}
	public void setTpa(boolean tpa) {
		this.tpa = tpa;
	}
	public boolean isGlobal() {
		return global;
	}
	public void setGlobal(boolean global) {
		this.global = global;
	}
	public static List<Toggle> getAll(){
		return Main.instance.cache.values().stream().collect(Collectors.toList());
	}
	public void save() {
		Main.instance.data.set("Toggle." + getJogador() + ".Tell", isTell());
		Main.instance.data.set("Toggle." + getJogador() + ".Coins", isCoins());
		Main.instance.data.set("Toggle." + getJogador() + ".Tpa", isTpa());
		Main.instance.data.set("Toggle." + getJogador() + ".Global", isGlobal());
		Main.instance.data.saveConfig();
	}
	
}
