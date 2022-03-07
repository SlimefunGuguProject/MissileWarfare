package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.WeaponUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import me.kaiyan.missilewarfare.VariantsAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MissileItem extends SlimefunItem {
    public MissileItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int type, String extraLore) {
        super(itemGroup, item, recipeType, recipe);

        MissileClass missileClass = VariantsAPI.missileStatsFromType(type);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add(ChatColors.color("&7范围: "+Math.sqrt(missileClass.range)));
        lore.add(ChatColors.color("&7威力: "+missileClass.power));
        lore.add(ChatColors.color("&7速度: "+missileClass.speed));
        lore.add(ChatColors.color("&7准确度: "+missileClass.accuracy+" 格内"));
        lore.add(ChatColors.color("&7" + extraLore));
        meta.setLore(lore);
        item.setItemMeta(meta);

        WeaponUseHandler attack = (event, player, itemStack) -> event.setCancelled(true);
        addItemHandler(attack);
    }
}