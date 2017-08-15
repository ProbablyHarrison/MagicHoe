package me.Harrison;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static me.Harrison.vars.*;

public class inventoryManager
        implements Listener
{
    public static ItemStack magicHoe()
    {
        ItemStack i = new ItemStack(Material.DIAMOND_HOE, 1);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(vars.magicHoeName);

        ArrayList<String> lore = new ArrayList() {};
        im.setLore(lore);

        im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, Boolean.TRUE.booleanValue());

        i.setItemMeta(im);

        return i;
    }

    public static ItemStack magicHammer()
    {
        ItemStack i = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta im = i.getItemMeta();

        im.setDisplayName(vars.magicHammerName);

        ArrayList<String> lore = new ArrayList() {};
        im.setLore(lore);

        im.addEnchant(Enchantment.DIG_SPEED, 4, Boolean.TRUE.booleanValue());

        i.setItemMeta(im);

        return i;
    }

    public static int invAmount = 27;
    public static Inventory toolsSelector = Bukkit.createInventory(null, invAmount, "MagicTools");

    static
    {
        for (int i = 0; i < invAmount; i++)
        {
            ItemStack it = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
            ItemMeta im = it.getItemMeta();
            im.setDisplayName(c("&7Blank"));
            it.setItemMeta(im);
            toolsSelector.setItem(i, it);
        }
        toolsSelector.setItem(10, magicHoe());

        toolsSelector.setItem(12, magicHammer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if (e.getInventory().equals(toolsSelector))
        {
            if (e.getCurrentItem().getItemMeta().equals(magicHoe().getItemMeta())) {
                e.getWhoClicked().getInventory().addItem(new ItemStack[] { e.getCurrentItem().clone() });
            }
            e.setCancelled(true);
        }
        if ((e.getView().getType() == InventoryType.ANVIL) &&
                (e.getRawSlot() == 2) &&
                (e.getInventory().getItem(0).getItemMeta().getDisplayName() != e.getInventory().getItem(2).getItemMeta().getDisplayName()) &&
                (e.getInventory().getItem(0).getItemMeta().equals(magicHoe().getItemMeta())))
        {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(c("You cannot repair the " + magicHoeName + " &rthis way!"));
            e.getWhoClicked().closeInventory();
        }
    }
}

