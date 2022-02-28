package hub.tpa;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownCleaner extends BukkitRunnable {
    
    public void start() {
        runTaskTimer(Main.getInstance(), 0, 20L * 60);
    }

    @Override
    public void run() {
        Map<UUID, Long> map = Main.getInstance().getCooldown();
        for (Entry<UUID, Long> entry : map.entrySet()) {
            if (((entry.getValue() - System.currentTimeMillis()) / 1000) <= 0) {
                map.remove(entry.getKey());
            }
        }
    }
    
}