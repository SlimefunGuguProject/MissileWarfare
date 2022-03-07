package org.slimefunguguproject.misslewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MissleWarfareItemStack extends SlimefunItemStack {
    public MissleWarfareItemStack(String id, Material material, String name, String... lore){
        super(id, material, "&a" + name, (im) -> {
            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();
                for (String line : lore) {
                    lines.add(ChatColors.color("&7" + line));
                }
                im.setLore(lines);
            }
        });
    }

    public MissleWarfareItemStack(String id, String texture, String name, String... lore){
        super(id, texture, "&a" + name, (im) -> {
            if (lore.length > 0) {
                List<String> lines = new ArrayList<>();
                for (String line : lore) {
                    lines.add(ChatColors.color("&7" + line));
                }
                im.setLore(lines);
            }
        });
    }
}
