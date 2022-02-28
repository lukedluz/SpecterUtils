package com.lucas.specterutils.tpa;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;

public class TpaCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;

        if (args.length < 1) {
            p.sendMessage("�cUse /tpa <jogador>.");
            return true;
        }

        UUID pid = p.getUniqueId();

        if (args[0].equalsIgnoreCase("aceitar")) {
            UUID tid = tpa.remove(pid);
            if (tid == null) {
                p.sendMessage("�cVoc� n�o recebeu nenhuma solicita��o de tpa.");
                return true;
            }

            Player t = Bukkit.getPlayer(tid);
            if (t == null) {
                p.sendMessage("�cEste jogador est� offline!");
                return true;
            }

            t.teleport(p.getLocation());
            t.sendMessage("�a" + p.getName() + " aceitou seu pedido de teleporte.");
            p.sendMessage("�aVoc� aceitou o pedido de teleporte de " + t.getName() + ".");
            return true;
        } else if (args[0].equalsIgnoreCase("negar")) {
            UUID tid = tpa.remove(pid);
            if (tid == null) {
                p.sendMessage("�cVoc� n�o recebeu nenhuma solicita��o de tpa.");
                return true;
            }

            Player t = Bukkit.getPlayer(tid);
            if (t == null) return true;

            t.sendMessage("�c" + p.getName() + " negou seu pedido de teleporte.");
            p.sendMessage("�cVoc� negou o pedido de teleporte de " + t.getName() + ".");
            return true;
        }

        Long ptime = cooldown.get(pid);

        if (ptime != null) {
            int falta = (int) (ptime - System.currentTimeMillis()) / 1000;
            if (falta > 0) {
                p.sendMessage("�cVoc� deve esperar �f" + falta + "s �cpara mandar outro pedido de teleporte.");
                return true;
            } else {
                cooldown.remove(pid);
            }
        }

        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
            p.sendMessage("�cEste jogador est� offline!");
            return true;
        }
        if (t.getUniqueId().equals(pid)) {
            p.sendMessage("�cEste jogador � voc�.");
            return true;
        }
        net.hubtoggle.object.Toggle te = net.hubtoggle.Main.instance.cache.get(t.getName());
        if(!te.isTpa()) {
            p.sendMessage("�cEste jogador est� com o recebimento de pedidos de teleporte desativado.");
            return true;
        }
        tpa.put(t.getUniqueId(), pid);
        cooldown.put(pid, System.currentTimeMillis() + 15000);

        p.sendMessage("�aVoc� enviou um pedido de teleporte para " + t.getName() + ".");

        t.sendMessage("�a" + p.getName() + " enviou um pedido de teleporte a voc�.");
        t.spigot().sendMessage(getAceitar());
        t.spigot().sendMessage(getNegar());
        return false;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        tpa.remove(e.getPlayer().getUniqueId());
        cooldown.remove(e.getPlayer().getUniqueId());
    }

    public Map<UUID, Long> getCooldown() {
        return cooldown;
    }

    private TextComponent getAceitar() {
        TextComponent text = new TextComponent("ACEITAR");
        text.setColor(ChatColor.GREEN);
        text.setBold(true);

        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { new TextComponent("�aClique aqui para aceitar!") }));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa aceitar"));

        return text;
    }

    private TextComponent getNegar() {
        TextComponent text = new TextComponent("NEGAR");
        text.setColor(ChatColor.RED);
        text.setBold(true);

        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { new TextComponent("�cClique aqui para negar!") }));
        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa negar"));

        return text;
    }

    public static hub.tpa.Main getInstance() {
        return main;
    }
}
