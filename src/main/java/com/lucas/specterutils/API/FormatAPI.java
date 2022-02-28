package com.lucas.specterutils.API;

import org.bukkit.event.Listener;

import com.lucas.specterutils.Main;

public class FormatAPI implements Listener {

	public static String format(final Double valor) {
		if (valor < 1000.0) {
			return Main.format(valor);
		}
		if (valor < 1000000.0) {
			return String.valueOf(String.valueOf(Main.format(valor / 1000.0))) + "k";
		}
		if (valor < 1.0E9) {
			return String.valueOf(String.valueOf(Main.format(valor / 1000000.0))) + "M";
		}
		if (valor < 1.0E12) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E9))) + "B";
		}
		if (valor < 1.0E15) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E12))) + "T";
		}
		if (valor < 1.0E18) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E15))) + "Q";
		}
		if (valor < 1.0E21) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E18))) + "QQ";
		}
		if (valor < 1.0E24) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E21))) + "S";
		}
		if (valor < 1.0E27) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E24))) + "SS";
		}
		if (valor < 1.0E30) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E27))) + "OC";
		}
		if (valor < 1.0E33) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E30))) + "N";
		}
		if (valor < 1.0E36) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E33))) + "D";
		}
		if (valor < 1.0E39) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E36))) + "UN";
		}
		if (valor < 1.0E42) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E39))) + "DUO";
		}
		if (valor < 1.0E45) {
			return String.valueOf(String.valueOf(Main.format(valor / 1.0E42))) + "TRE";
		}
		return String.valueOf(valor);
	}
}
