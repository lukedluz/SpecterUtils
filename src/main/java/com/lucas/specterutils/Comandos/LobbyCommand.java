package com.lucas.specterutils.Comandos;

import com.lucas.specterutils.Torre.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class LobbyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("Lobby");
			((Player) s).sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
		}
		return false;
	}

}
