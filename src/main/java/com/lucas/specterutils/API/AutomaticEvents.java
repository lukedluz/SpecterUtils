package com.lucas.specterutils.API;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.lucas.specterutils.Main;

public class AutomaticEvents {

	public static void AutomaticEvent() {
		new BukkitRunnable() {
			public void run() {
				Date now = new Date();
				SimpleDateFormat format = new SimpleDateFormat("EE;HH;mm");
				String[] split = format.format(now).split(";");
				String dia = split[0];
				String hora = split[1];
				String minuto = split[2];
				if (hora.equalsIgnoreCase("23") && minuto.equalsIgnoreCase("00")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "darbooster global 30 2");
				}
				if (dia.equalsIgnoreCase("sat") && hora.equalsIgnoreCase("17") && minuto.equalsIgnoreCase("00")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "killer forcestart");
				}
				if (dia.equalsIgnoreCase("sun") && hora.equalsIgnoreCase("17") && minuto.equalsIgnoreCase("00")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gladiador iniciar");
				}
				if (hora.equalsIgnoreCase("15") && minuto.equalsIgnoreCase("00")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minarecheada iniciar");
				}
				if (hora.equalsIgnoreCase("14") && minuto.equalsIgnoreCase("00")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fight iniciar");
				}
				if (hora.equalsIgnoreCase("11") && minuto.equalsIgnoreCase("40")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload atlaseventoschat");
				}
				if (hora.equalsIgnoreCase("16") && minuto.equalsIgnoreCase("40")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload atlaseventoschat");
				}
				if (hora.equalsIgnoreCase("21") && minuto.equalsIgnoreCase("40")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "plugman reload atlaseventoschat");
				}
			}
		}.runTaskTimer(Main.m, 0L, 60 * 20L);
	}
}
