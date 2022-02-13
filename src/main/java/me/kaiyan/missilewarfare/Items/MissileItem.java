package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
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
        lore.add("&7范围: "+missileClass.range);
        lore.add("&7动力: "+missileClass.power);
        lore.add("&7速度: "+missileClass.speed);
        lore.add("&7准确度: 约在 "+missileClass.accuracy+" 格内");
        lore.add(extraLore);
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
}
