package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class GuideBook extends SlimefunItem {
    public GuideBook(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        BookMeta meta = (BookMeta) item.getItemMeta();

        meta.setTitle("导弹食用手册");
        meta.setAuthor("Kaiyan2006");

        BaseComponent[] homepage = new ComponentBuilder(ChatColor.BOLD+"导弹科技\n").color(ChatColor.BOLD)
                .append("这本向导书是介绍导弹科技的\n")
                .append(ChatColor.BOLD+"目录:\n")
                .append(ChatColor.BLUE+"导弹发射井\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"))
                .append(ChatColor.BLUE+"防空发射井\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "3"))
                .create();
        BaseComponent[] groundMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"导弹发射井\n")
                .append("导弹发射井可以向给定坐标发射导弹\n\n")
                .append("手持木棍右击以设置发射坐标\n")
                .append("手持烈焰棒右击以设置")
                .append("巡航高度\n")
                .create();

        meta.spigot().addPage(homepage);
        meta.spigot().addPage(groundMissileLauncher);

        item.setItemMeta(meta);
    }
}
