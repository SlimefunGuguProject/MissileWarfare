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
            .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"对地导弹发射井\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"))
            .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"反弹道导弹发射井\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "3"))
            .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"便携式防空导弹\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "4"))
            .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"反鞘翅导弹发射井").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "5"))
            .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"敌我识别系统").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "6"))
            .create();
        BaseComponent[] groundMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"对地导弹发射井\n")
            .append("导弹发射井可以向给定坐标发射导弹\n\n")
            .append("手持木棍右击以设置发射坐标\n")
            .append("手持烈焰棒右击以设置巡航高度")
            .append(ChatColor.BOLD+"会命中己方导弹")
            .create();
        BaseComponent[] antiMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"反弹道导弹发射井\n")
            .append("反弹道导弹发射井会尝试拦截The Anti-Missile Launcher targets and shoots down any incoming missiles.\n")
            .append("It will shoot down any missiles, whether friendly or not, that comes into its range\n")
            .append("Use a redstone input to disable it from firing.")
            .create();

        BaseComponent[] manpad = new ComponentBuilder(ChatColor.BOLD+"便携式防空导弹\n")
            .append("便携式防空导弹可以识别敌方导弹并拦截\n")
            .append("与反弹道导弹发射井不同，便携式防空导弹保证可以击落敌方来袭的导弹\n")
            .append("按住Shift来进行导弹识别")
            .create();

        BaseComponent[] antiElytraLaunchers = new ComponentBuilder(ChatColor.BOLD+"防空导弹发射井\n")
            .append("防空导弹发射井可以发射导弹来破坏进入射程的玩家身上的鞘翅\n")
            .append("这在战争期间保卫城市时非常好用\n")
            .append("使用敌我识别系统来设置忽略的目标\n")
            .append("手持木棍右键点击来设置")
            .create();

        BaseComponent[] playerID = new ComponentBuilder(ChatColor.BOLD+"敌我识别系统\n")
            .append("敌我识别系统可以用于让部分发射井忽略指定的玩家\n")
            .append("使用敌我识别系统配置器以进行设置\n")
            .append(ChatColor.BOLD+"任何人都可以设置任何ID，不要向别人透露你的ID\n")
            .create();


        meta.spigot().addPage(homepage);
        meta.spigot().addPage(groundMissileLauncher);
        meta.spigot().addPage(antiMissileLauncher);
        meta.spigot().addPage(manpad);
        meta.spigot().addPage(antiElytraLaunchers);
        meta.spigot().addPage(playerID);

        item.setItemMeta(meta);
    }
}
