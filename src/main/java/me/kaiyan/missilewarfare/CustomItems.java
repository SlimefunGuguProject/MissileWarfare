package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.kaiyan.missilewarfare.Blocks.AntiMissileLauncher;
import me.kaiyan.missilewarfare.Blocks.GroundMissileLauncher;
import me.kaiyan.missilewarfare.Items.GuideBook;
import me.kaiyan.missilewarfare.Items.ManPad;
import me.kaiyan.missilewarfare.Items.MissileItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class CustomItems {
    public static void setup(){
        //Add section to guide
        NamespacedKey categoryId = new NamespacedKey(MissileWarfare.getInstance(), "missile_warfare");
        CustomItemStack categoryItem = new CustomItemStack(Material.GUNPOWDER, "&6导弹科技");

        ItemGroup group = new ItemGroup(categoryId, categoryItem);

        //Add Items
        //SUGARFUEL
        SlimefunItemStack sugarfuelstack = new SlimefunItemStack("SUGARFUEL", Material.SUGAR, "&f&l糖燃料", "&7便宜，高能量，非常适合小型导弹");
        ItemStack[] sugarfuelrecipe = {
                null, SlimefunItems.MAGNESIUM_DUST, null,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
                null, SlimefunItems.MAGNESIUM_DUST, null
        };

        SlimefunItem sugarfuel = new SlimefunItem(group, sugarfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, sugarfuelrecipe);
        //-SUGARFUEL-
        //INFOITEM
        SlimefunItemStack guidestack = new SlimefunItemStack("GUIDEBOOK", Material.WRITTEN_BOOK, "&b&l导弹指南书", "&7它真的可以指挥你吗?!?");
        ItemStack[] guiderecipe = {
                null, sugarfuelstack, null,
                sugarfuelstack, new ItemStack(Material.BOOK), sugarfuelstack,
                null, sugarfuelstack, null
        };

        GuideBook guide = new GuideBook(group, guidestack, RecipeType.ENHANCED_CRAFTING_TABLE, guiderecipe);

        sugarfuel.register(MissileWarfare.getInstance());
        guide.register(MissileWarfare.getInstance());
        //-INFOITEM-
        // EXPLOSIVE POWDER
        SlimefunItemStack explosivepowderstack = new SlimefunItemStack("EXPLOSIVEPOWDER", Material.GLOWSTONE_DUST, "&e&l炸药粉", "&7小心轻放!");
        ItemStack[] explosivepowderrecipe = {
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST
        };

        SlimefunItem explosivepowder = new SlimefunItem(group, explosivepowderstack, RecipeType.ENHANCED_CRAFTING_TABLE, explosivepowderrecipe);

        explosivepowder.register(MissileWarfare.getInstance());
        //-EXPLOSIVE POWDER-
        SlimefunItemStack compressedpowderstack = new SlimefunItemStack("COMPRESSEDEXPLOSIVES", Material.YELLOW_CONCRETE, "&8&l固态炸药粉", "&7炸药粉被压缩成了固体");
        ItemStack[] compressedpowderrecipe = {
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack,
                SlimefunItems.MAGNESIUM_DUST, explosivepowderstack, SlimefunItems.MAGNESIUM_DUST,
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };

        SlimefunItem compressedpowder = new SlimefunItem(group, compressedpowderstack, RecipeType.COMPRESSOR, compressedpowderrecipe);

        compressedpowder.register(MissileWarfare.getInstance());
        // ULTRALITE INGOT
        SlimefunItemStack ultraliteingotstack = new SlimefunItemStack("ULTRALITE_INGOT", Material.BRICK, "&c&l超轻铸锭", "&7用于导弹的超轻型铸锭");
        ItemStack[] ultraliteingotrecipe = {
                SlimefunItems.IRON_DUST, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.COPPER_DUST,
                null, null, null,
                null, null, null
        };

        SlimefunItem ultraliteingot = new SlimefunItem(group, ultraliteingotstack, RecipeType.SMELTERY, ultraliteingotrecipe);

        ultraliteingot.register(MissileWarfare.getInstance());
        //-ULTRALITE INGOT-
        // ULTRALITE PLATE
        SlimefunItemStack ultraliteplatestack = new SlimefunItemStack("ULTRALITE_PLATE", Material.IRON_INGOT, "&6&l超轻铸板", "&7用于导弹的超轻型铸板");
        ItemStack[] ultraliteplaterecipe = {
                ultraliteingotstack, null, ultraliteingotstack,
                null, new ItemStack(Material.COAL), null,
                ultraliteingotstack, null, ultraliteingotstack
        };

        SlimefunItem ultraliteplate = new SlimefunItem(group, ultraliteplatestack, RecipeType.COMPRESSOR, ultraliteplaterecipe);

        ultraliteplate.register(MissileWarfare.getInstance());
        //-ULTRALITE PLATE-
        // Flight Computer Simple
        SlimefunItemStack simpleflightcomputerstacks = new SlimefunItemStack("SIMPLE_FLIGHT_COMPUTER", Material.POWERED_RAIL, "&b&l基础飞行计算机", "&7能够引导导弹的简单计算机");
        SlimefunItemStack simpleflightcomputerstack = (SlimefunItemStack) simpleflightcomputerstacks.clone();
        simpleflightcomputerstacks.setAmount(16);
        simpleflightcomputerstack.setAmount(1);
        ItemStack[] simpleflightcomputerrecipe = {
                ultraliteingotstack, SlimefunItems.BASIC_CIRCUIT_BOARD, ultraliteingotstack,
                new ItemStack(Material.REDSTONE), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.REDSTONE),
                ultraliteingotstack, SlimefunItems.BASIC_CIRCUIT_BOARD, ultraliteingotstack
        };

        SlimefunItem simpleflightcomputer = new SlimefunItem(group, simpleflightcomputerstack, RecipeType.ENHANCED_CRAFTING_TABLE, simpleflightcomputerrecipe);
        simpleflightcomputer.setRecipeOutput(simpleflightcomputerstacks);
        simpleflightcomputer.register(MissileWarfare.getInstance());
        //-Flight Computer Simple-
        // Radar Part
        SlimefunItemStack radarstack = new SlimefunItemStack("RADAR", Material.ACTIVATOR_RAIL, "&9&l雷达", "&7引导防空导弹时使用");
        ItemStack[] radarrecipe = {
                null, ultraliteplatestack, null,
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                new ItemStack(Material.REDSTONE), null, new ItemStack(Material.REDSTONE)
        };

        SlimefunItem radar = new SlimefunItem(group, radarstack, RecipeType.ENHANCED_CRAFTING_TABLE, radarrecipe);

        radar.register(MissileWarfare.getInstance());
        //-Radar Part-
        // ROCKET FUEL
        SlimefunItemStack rocketfuelstack = new SlimefunItemStack("ROCKETFUEL", Material.GUNPOWDER, "&4火箭燃料", "&7以1000个煤的功率燃烧...");
        ItemStack[] rocketfuelrecipe = {
                new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.GLOWSTONE_DUST),
                new ItemStack(Material.GUNPOWDER), new ItemStack(Material.COAL), new ItemStack(Material.GUNPOWDER),
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };
        SlimefunItem rocketfuel = new SlimefunItem(group, rocketfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, rocketfuelrecipe);

        rocketfuel.register(MissileWarfare.getInstance());
        //-ROCKETFUEL-
        //simple WARHEAD
        SlimefunItemStack smallwarheadstack = new SlimefunItemStack("SMALLWARHEAD", Material.TNT, "&a&l基础导弹弹头", "&7用于制造导弹", "&7'请勿触碰红色部分'");
        ItemStack[] smallwarheadrecipe = {
                null, SlimefunItems.ALUMINUM_INGOT, null,
                SlimefunItems.ALUMINUM_INGOT, explosivepowderstack, SlimefunItems.ALUMINUM_INGOT,
                null, null, null
        };

        SlimefunItem smallwarhead = new SlimefunItem(group, smallwarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallwarheadrecipe);

        smallwarhead.register(MissileWarfare.getInstance());
        //-SMALL WARHEAD-
        //SMALL BODY
        SlimefunItemStack smallbodystack = new SlimefunItemStack("SMALLBODY", Material.IRON_BLOCK, "&a&l基础导弹体", "&7用于制造导弹", "&7'请勿削弱防护层'");
        ItemStack[] smallbodyrecipe = {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                ultraliteingotstack, simpleflightcomputerstack, ultraliteingotstack,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallbody = new SlimefunItem(group, smallbodystack, RecipeType.ENHANCED_CRAFTING_TABLE, smallbodyrecipe);

        smallbody.register(MissileWarfare.getInstance());
        //-SMALL BODY-
        // SMALL FINS
        SlimefunItemStack smallfinstack = new SlimefunItemStack("SMALLFIN", Material.IRON_BOOTS, "&f&l基础导弹鳍", "&7用于制造导弹");
        ItemStack[] smallfinrecipe = {
                null, null, null,
                null, simpleflightcomputerstack, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallfin = new SlimefunItem(group, smallfinstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallfinrecipe);

        smallfin.register(MissileWarfare.getInstance());
        //-SMALL FINS-
        //SMALL GtG MISSILE
        SlimefunItemStack smallmissilestack = new SlimefunItemStack("SMALLMISSILE", Material.IRON_SWORD, "&d&l基础GtG导弹", "&7小型地对地导弹","&7无变体");
        ItemStack[] smallmissilerecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissile = new MissileItem(group, smallmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipe, 1, "'&7小Timmy从来没有机会...'");

        smallmissile.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE-
        //SMALL GtG MISSILE HE
        SlimefunItemStack smallmissilestackHE = new SlimefunItemStack("SMALLMISSILEHE", Material.IRON_SWORD, "&6&l基础GtG导弹 &e&lHE", "&7小型地对地导弹","&7高爆炸变体");
        ItemStack[] smallmissilerecipeHE = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                explosivepowderstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissileHE = new MissileItem(group, smallmissilestackHE, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipeHE, 2, "'&7大Timmy从来没有机会...'");

        smallmissileHE.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE HE-
        // SMALL GtG MISSILE LR
        SlimefunItemStack smallmissileLRstack = new SlimefunItemStack("SMALLMISSILELR", Material.IRON_SWORD,"&c&l基础GtG导弹 &e&lLR","&7长距离变体");
        ItemStack[] smallmissileLRrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack
        };
        MissileItem smallmissileLR = new MissileItem(group, smallmissileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileLRrecipe, 3, "'&7远处的timmy从来没有机会...'");

        smallmissileLR.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE LR-
        // SMALL GtG MISSILE AC
        SlimefunItemStack smallmissileACstack = new SlimefunItemStack("SMALLMISSILEAC", Material.IRON_SWORD,"&4&l基础GtG导弹 &e&lAC","&7精确制导变体");
        ItemStack[] smallmissileACrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                smallfinstack, rocketfuelstack, smallfinstack
        };
        MissileItem smallmissileAC = new MissileItem(group, smallmissileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileACrecipe, 4, "'&7超小timmy从来没有机会...'");

        smallmissileAC.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE AC-
        // Small GtG MISSILE LAUNCHER 1x
        SlimefunItemStack groundlauncherstack = new SlimefunItemStack("GROUNDLAUNCHER", Material.DISPENSER, "&b&l导弹发射井", "&7用于打击地面上的特定区域", "&7使用木棍设定目标坐标", "&7手持木棍Shift单击以检查它是否能开火", "&7手持烈焰棒右键单击以设置巡航高度","&7默认巡航高度为Y:120", "&7需要建在一块绿色混凝土上");
        ItemStack[] groundlauncherrecipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };
        GroundMissileLauncher groundlauncher = new GroundMissileLauncher(group, groundlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, groundlauncherrecipe);

        groundlauncher.register(MissileWarfare.getInstance());
        //- Small GtG MISSILE LAUNCHER-
        //MISSILE BODY
        SlimefunItemStack missilebodystack = new SlimefunItemStack("MISSILE_BODY", Material.SMOOTH_STONE, "&d&l导弹体", "&7内含飞行计算机的导弹体");
        ItemStack[] missilebodyrecipe = {
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack
        };

        SlimefunItem missilebody = new SlimefunItem(group, missilebodystack, RecipeType.ENHANCED_CRAFTING_TABLE, missilebodyrecipe);

        missilebody.register(MissileWarfare.getInstance());
        // - MISSILE BODY-
        // Advanced Fins
        SlimefunItemStack finsstack = new SlimefunItemStack("MISSILE_FINS", Material.GOLDEN_BOOTS, "&f&l导弹鳍", "&7它能够更好地移动和引导导弹");
        ItemStack[] finsrecipe = {
                null, null, null,
                ultraliteplatestack, null, ultraliteplatestack,
                ultraliteplatestack, null, ultraliteplatestack
        };

        SlimefunItem fins = new SlimefunItem(group, finsstack, RecipeType.ENHANCED_CRAFTING_TABLE, finsrecipe);

        fins.register(MissileWarfare.getInstance());
        // - ADVANCED FINS-
        // GtA MISSILE
        SlimefunItemStack antiAirMissilestack = new SlimefunItemStack("ANTIAIRMISSILE", Material.IRON_SWORD,"&c&l防空导弹","&7可击落空中目标", "&7安装于反导弹发射地井");
        ItemStack[] antiAirMissilerecipe = {
                null, radarstack, null,
                explosivepowderstack, missilebodystack, explosivepowderstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem antiAirMissile = new MissileItem(group, antiAirMissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, antiAirMissilerecipe, 5, "'&7空降Timmy从来没有机会'");

        antiAirMissile.register(MissileWarfare.getInstance());
        //-GtA MISSILE
        // GtA MISSILE LAUNCHER 1x
        SlimefunItemStack antiairlauncherstack = new SlimefunItemStack("ANTIMISSILELAUNCHER", Material.DISPENSER, "&9&l防空发射井", "&7瞄准并击落该地区的其他导弹", "&7需红石操控开关", "&7发射井周围须被煤炭块包围", "&7(并非沿对角线包围)");
        ItemStack[] antiairlauncherrecipe = {
                SlimefunItems.SILVER_INGOT, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SILVER_INGOT,
                SlimefunItems.SILVER_INGOT, null, SlimefunItems.SILVER_INGOT,
                SlimefunItems.LEAD_INGOT, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.LEAD_INGOT
        };
        AntiMissileLauncher antiairlauncher = new AntiMissileLauncher(group, antiairlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, antiairlauncherrecipe);

        antiairlauncher.register(MissileWarfare.getInstance());
        //- GtA MISSILE LAUNCHER-
        //MANPAD
        SlimefunItemStack manpadstack = new SlimefunItemStack("MANPAD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFiNmVlNWJiZTVhZDQyOTY4MGMxYzE1Y2Y0MjBmOTgxMWUxMTRiNzY4NTRmODk5ZjBlZjA4ZmRlMzMyNzk4YyJ9fX0=", "&6&l便携式防空导弹", "&7一种手持反导设备", "&7Shift+RMB 开始追踪", "&7松开Shift 发射导弹");
        ItemStack[] manpadrecipe = {
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                explosivepowderstack, sugarfuelstack, sugarfuelstack,
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
        };
        ManPad manpad = new ManPad(group, manpadstack, RecipeType.ENHANCED_CRAFTING_TABLE, manpadrecipe);
        manpad.register(MissileWarfare.getInstance());
        //-MANPAD-

        // GtGMissile
        SlimefunItemStack missilestack = new SlimefunItemStack("MISSILE", Material.GOLDEN_SWORD,"&d&lGtG导弹","&7无变体");
        ItemStack[] missilerecipe = {
                null, smallwarheadstack, null,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missile = new MissileItem(group, missilestack, RecipeType.ENHANCED_CRAFTING_TABLE, missilerecipe, 6, "'Your friendly neighbourhood missile'");

        missile.register(MissileWarfare.getInstance());
        //-GtGMissile-
        // GtGMissileHE
        SlimefunItemStack missileHEstack = new SlimefunItemStack("MISSILEHE", Material.GOLDEN_SWORD,"&6&lGtG导弹 &e&lHE","&7高爆炸变体");
        ItemStack[] missileHErecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileHE = new MissileItem(group, missileHEstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileHErecipe, 7, "'Your not-so friendly neighbourhood missile'");

        missileHE.register(MissileWarfare.getInstance());
        //-GtGMissileHE-
        // GtGMissileLR
        SlimefunItemStack missileLRstack = new SlimefunItemStack("MISSILELR", Material.GOLDEN_SWORD,"&c&lGtG导弹 &e&lLR","&7长距离变体");
        ItemStack[] missileLRrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileLR = new MissileItem(group, missileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileLRrecipe, 8, "'Your friendly state missile'");

        missileLR.register(MissileWarfare.getInstance());
        //-GtGMissileLR-
        // GtGMissileAC
        SlimefunItemStack missileACstack = new SlimefunItemStack("MISSILEAC", Material.GOLDEN_SWORD,"&c&lGtG导弹 &e&lAC","&7精确制导变体");
        ItemStack[] missileACrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileAC = new MissileItem(group, missileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileACrecipe, 9, "'Your friendly home-defending missile'");

        missileAC.register(MissileWarfare.getInstance());
        //-GtGMissileAC-

        //template
        /*SlimefunItemStack %stack = new SlimefunItemStack("%", Material., "N", "L");
        ItemStack[] %recipe = {
                null, null, null,
                null, null, null,
                null, null, null
        };

        SlimefunItem % = new SlimefunItem(group, %stack, RecipeType.ENHANCED_CRAFTING_TABLE, %recipe);

        %.register(AdvancedWarfare.getInstance());*/
        //Missile template =================
        /*
        SlimefunItemStack %stack = new SlimefunItemStack("%", Material.DISPENSER,"Small % Missile","Normal Variant", "Range: % Blocks", "Power: %", "Speed: %", "Accuracy: Within % blocks", "'%'");
        ItemStack[] %recipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };
        SlimefunItem % = new SmallGroundMissileLauncher(group, %stack, RecipeType.ENHANCED_CRAFTING_TABLE, %recipe);

        %.register(AdvancedWarfare.getInstance());
         */

        //ADD RESEARCH
        NamespacedKey basicfuelkey = new NamespacedKey(MissileWarfare.getInstance(), "basic_fuel");
        Research basicfuel = new Research(basicfuelkey, 3467341, "不可食用的糖", 12);
        basicfuel.addItems(sugarfuel);
        basicfuel.register();

        NamespacedKey explosiveskey = new NamespacedKey(MissileWarfare.getInstance(), "explosives");
        Research explosives = new Research(explosiveskey, 3447321, "爆炸就是艺术", 15);
        explosives.addItems(explosivepowder);
        explosives.register();

        NamespacedKey groundlauncherskey = new NamespacedKey(MissileWarfare.getInstance(), "groundlauncher");
        Research groundlauncherres = new Research(groundlauncherskey, 34117322, "导弹发射井", 55);
        groundlauncherres.addItems(groundlauncher);
        groundlauncherres.register();

        NamespacedKey smallgmissilepartskey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissileparts");
        Research smallgmissileparts = new Research(smallgmissilepartskey, 2667313, "进阶导弹", 30);
        smallgmissileparts.addItems(smallwarhead, smallbody, smallfin);
        smallgmissileparts.register();

        NamespacedKey smallgmissilekey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissile");
        Research smallgmissile = new Research(smallgmissilekey, 35673323, "彩虹色！", 25);
        smallgmissile.addItems(smallmissile, smallmissileHE, smallmissileLR, smallmissileLR);
        smallgmissile.register();

        NamespacedKey advancedfuelkey = new NamespacedKey(MissileWarfare.getInstance(), "advancedfuel");
        Research advancedfuel = new Research(advancedfuelkey, 3461423, "高级(甚至更难食用)燃料!", 22);
        advancedfuel.addItems(rocketfuel);
        advancedfuel.register();

        NamespacedKey missilepartskey = new NamespacedKey(MissileWarfare.getInstance(), "missileparts");
        Research missileparts = new Research(missilepartskey, 4461423, "进阶PLUS导弹", 60);
        missileparts.addItems(ultraliteingot, ultraliteplate, simpleflightcomputer, radar);
        missileparts.register();
    }
}
