package com.lucas.specterutils.tab;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabEvent implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {

        net.hubtab.Tablist.sendTablist(e.getPlayer(),
                " \n \n            �e�lHUB NETWORK           \n �7      o melhor server de �f�nfactions raiz�7. \n ",
                " \n\n�fSite�7: http://hubnetwork.top. \n");

    }
}
