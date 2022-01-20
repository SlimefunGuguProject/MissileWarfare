package me.kaiyan.advancedwarfare.Blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockDispenseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import jdk.internal.icu.util.CodePointTrie;
import me.kaiyan.advancedwarfare.AdvancedWarfare;
import me.kaiyan.advancedwarfare.CustomItems;
import me.kaiyan.advancedwarfare.Items.SmallGtGMissile;
import me.kaiyan.advancedwarfare.Missiles.MissileController;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.TileState;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Objects;

public class GroundMissileLauncher extends SlimefunItem {
    private final SmallGtGMissile itemMissile;

    public GroundMissileLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, SmallGtGMissile itemMissile) {
        super(itemGroup, item, recipeType, recipe);
        this.itemMissile = itemMissile;
    }

    @Override
    public void preRegister() {
        //cancel thing on place
        BlockPlaceHandler blockPlaceHandler = new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                World world = event.getBlock().getWorld();
                Block below = world.getBlockAt(event.getBlock().getLocation().subtract(new Vector(0, 1, 0)));
                Block bottom = world.getBlockAt(event.getBlock().getLocation().subtract(new Vector(0, 2, 0)));
                if (below.getType() == Material.GREEN_CONCRETE){
                    if (bottom.getType() == Material.GREEN_CONCRETE){
                        event.getPlayer().sendMessage("Created Tier 1 Launcher!");
                    }else{
                        event.getPlayer().sendMessage("Bottom Block is type: " + bottom.getType() + " It needs Type GREEN_CONCRETE");
                        event.setCancelled(true);
                    }
                }else{
                    event.getPlayer().sendMessage("Middle Block is type: " + bottom.getType() + " It needs Type GREEN_CONCRETE");
                    event.setCancelled(true);
                }
            }
        };
        addItemHandler(blockPlaceHandler);

        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);

        BlockDispenseHandler blockDispenseHandler = this::blockDispense;
        addItemHandler(blockDispenseHandler);
    }

    private void blockDispense(BlockDispenseEvent blockDispenseEvent, Dispenser dispenser, Block block, SlimefunItem slimefunItem) {
        fireMissile(dispenser);
        blockDispenseEvent.setCancelled(true);
    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
        if (event.getItem().getType() == Material.STICK){
            TileState _state = (TileState) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
            PersistentDataContainer _cont = _state.getPersistentDataContainer();
            if (event.getPlayer().isSneaking()){
                int[] coords = _cont.get(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY);
                float dist = (float) new Vector(coords[0], 0, coords[1]).distanceSquared(new Vector(event.getInteractEvent().getClickedBlock().getX(),0, event.getInteractEvent().getClickedBlock().getY()));
                if (dist > (2000*2000)) {
                    event.getPlayer().sendMessage("The coords are: " + coords[0]+","+coords[1]+" And the DIST is: "+dist + " So The missile WONT Fire");
                    return;
                } else {
                    event.getPlayer().sendMessage("The coords are: " + coords[0]+","+coords[1]+" And the DIST is: "+dist + " So The missile WILL Fire");
                    return;
                }
            }
            event.cancel();

            TileState state = (TileState) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
            PersistentDataContainer cont = state.getPersistentDataContainer();

            Prompt askCoordY = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "Input Coordinates Y";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        cont.set(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY, new int[]{cont.get(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY)[0], Integer.parseInt(s)});
                    } catch (NumberFormatException e){
                        conversationContext.getForWhom().sendRawMessage("NOT A INT NUMBER");
                        return END_OF_CONVERSATION;
                    }
                    conversationContext.getForWhom().sendRawMessage("Y: "+Integer.parseInt(s));
                    state.update();
                    return END_OF_CONVERSATION;
                }
            };
            Prompt askCoordX = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "Input Coordinates X";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        cont.set(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY, new int[] {Integer.parseInt(s), 0});
                    } catch (NumberFormatException e){
                        conversationContext.getForWhom().sendRawMessage("NOT A COORD");
                        return END_OF_CONVERSATION;
                    }
                    conversationContext.getForWhom().sendRawMessage("X: "+Integer.parseInt(s));
                    return askCoordY;
                }
            };

            ConversationFactory cf = new ConversationFactory(AdvancedWarfare.getInstance());
            Conversation conversation = cf.withFirstPrompt(askCoordX)
                    .withLocalEcho(false)
                    .buildConversation(event.getPlayer());
            conversation.begin();
        }
    }

    public boolean hasAmmo(Inventory inv, SmallGtGMissile missile){
        for (ItemStack item : inv) {
            if (missile.isItem(item)){
                inv.clear(inv.first(item));
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public void fireMissile(PlayerRightClickEvent event){
        Dispenser disp = (Dispenser) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
        if (hasAmmo(disp.getInventory(), itemMissile)){
            TileState state = (TileState) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
            PersistentDataContainer cont = state.getPersistentDataContainer();
            int[] coords = cont.get(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY);
            event.getPlayer().sendMessage(Arrays.toString(coords));
            if (coords == null) {
                event.getPlayer().sendMessage("You need to input coordinates!");
                return;
            }
            MissileController missile = new MissileController(true, event.getInteractEvent().getClickedBlock().getLocation().add(new Vector(0.5, 1, 0.5)).toVector(), new Vector(coords[0], 0, coords[1]), 1, event.getPlayer().getWorld(), 3, 30);
            missile.FireMissile();
        }
    }

    public void fireMissile(Dispenser disp){
        if (hasAmmo(disp.getInventory(), itemMissile)){
            TileState state = (TileState) disp.getBlock().getState();
            PersistentDataContainer cont = state.getPersistentDataContainer();
            int[] coords = cont.get(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY);
            if (coords == null) {
                AdvancedWarfare.getInstance().getServer().broadcastMessage("Missile cannot fire at : "+disp.getBlock().getLocation() + " Invalid Coordinates!");
                return;
            } else if (new Vector(coords[0], 0, coords[1]).distanceSquared(new Vector(disp.getX(),0, disp.getY())) > (2000*2000)){
                AdvancedWarfare.getInstance().getServer().broadcastMessage("Missile cannot fire at : "+disp.getBlock().getLocation() + " Too Far Away!");
            }
            MissileController missile = new MissileController(true, disp.getBlock().getLocation().add(new Vector(0.5, 1, 0.5)).toVector(), new Vector(coords[0], 0, coords[1]), 1, disp.getBlock().getWorld(), 4, 30);
            missile.FireMissile();
        } else {
            AdvancedWarfare.getInstance().getServer().broadcastMessage("No ammo");
        }
    }
}
