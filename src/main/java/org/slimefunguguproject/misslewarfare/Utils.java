package org.slimefunguguproject.misslewarfare;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.entity.Player;

public class Utils {
    private Utils() {}

    public static void send(Player p, String message) {
        p.sendMessage(ChatColors.color(message));
    }
}
