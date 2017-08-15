package me.Harrison;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class listeners
        implements Listener, CommandExecutor
{
    private final main plugin;

    public listeners(main p)
    {
        this.plugin = p;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (commandLabel.equalsIgnoreCase(vars.mainCommand)) {
            if ((sender instanceof Player))
            {
                if (sender.isOp())
                {
                    Player p = Bukkit.getPlayer(sender.getName());
                    if (args.length <= 0)
                    {
                        p.sendMessage(vars.c("You did &conly &6/" + vars.mainCommand));
                        p.openInventory(inventoryManager.toolsSelector);
                    }
                    else
                    {
                        String msg = vars.c("You did &6/" + vars.mainCommand + " &rwith &a" + args.length + " argument");
                        if (args.length == 1)
                        {
                            if (args[0].equalsIgnoreCase("radius")) {
                                p.sendMessage("done");
                            }
                        }
                        else {
                            p.sendMessage(msg + "s");
                        }
                    }
                }
                else
                {
                    sender.sendMessage("You need to be OP to use this.");
                }
            }
            else {
                sender.sendMessage(vars.c("You need &cto be a player&r to execute this command."));
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if ((e.getClickedBlock().getType().equals(Material.CROPS)) &&
                    (e.getClickedBlock().getData() != 7) &&
                    (e.getPlayer().getItemInHand().hasItemMeta()) &&
                    (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE)) && (e.getPlayer().getItemInHand().getItemMeta().equals(inventoryManager.magicHoe().getItemMeta())))
            {
                growMe(e.getClickedBlock());
                radiusChange(e.getClickedBlock(), 2);
                e.getClickedBlock().getLocation().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ITEM_PICKUP, 5.0F, 5.0F);
            }
            if ((e.getClickedBlock().getType().equals(Material.POTATO)) &&
                    (e.getClickedBlock().getData() != 7) &&
                    (e.getPlayer().getItemInHand().hasItemMeta()) &&
                    (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE)) && (e.getPlayer().getItemInHand().getItemMeta().equals(inventoryManager.magicHoe().getItemMeta())))
            {
                growMe(e.getClickedBlock());
                radiusChange(e.getClickedBlock(), 2);
                e.getClickedBlock().getLocation().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ITEM_PICKUP, 5.0F, 5.0F);
            }
            if ((e.getClickedBlock().getType().equals(Material.CARROT)) &&
                    (e.getClickedBlock().getData() != 7) &&
                    (e.getPlayer().getItemInHand().hasItemMeta()) &&
                    (e.getPlayer().getItemInHand().getType().equals(Material.DIAMOND_HOE)) && (e.getPlayer().getItemInHand().getItemMeta().equals(inventoryManager.magicHoe().getItemMeta())))
            {
                growMe(e.getClickedBlock());
                radiusChange(e.getClickedBlock(), 2);
                e.getClickedBlock().getLocation().getWorld().playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ITEM_PICKUP, 5.0F, 5.0F);
            }
        }
    }

    public static void radiusChange(Block b, int r)
    {
        for (int x = r * -1; x <= r; x++) {
            for (int y = r * -1; y <= r; y++) {
                for (int z = r * -1; z <= r; z++)
                {
                    Block blocky = b.getWorld().getBlockAt(b.getX() + x, b.getY() + y, b.getZ() + z);
                    if ((blocky.getType() == Material.CROPS) || (blocky.getType() == Material.POTATO) || (blocky.getType() == Material.CARROT)) {
                        growMe(blocky);
                    }
                }
            }
        }
    }

    public static void breakMe(Block b, Player p, BlockBreakEvent e)
    {
        Random r = new Random();

        int num = r.nextInt(6) + 2;
        if (b.getData() == 7)
        {
            b.getLocation().getWorld().playEffect(b.getLocation().add(0.5D, 0.5D, 0.5D), Effect.CRIT, 3);
            b.getLocation().getWorld().playSound(b.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5.0F, 5.0F);
            if (b.getType().equals(Material.CARROT))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.CARROT_ITEM, num) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
            if (b.getType().equals(Material.POTATO))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.POTATO_ITEM, num) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
            if (b.getType().equals(Material.CROPS))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WHEAT, num) });
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SEEDS, num - 1) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
        }
        else
        {
            if (b.getType().equals(Material.CARROT))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.CARROT, 1) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
            if (b.getType().equals(Material.POTATO))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.POTATO_ITEM, 1) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
            if (b.getType().equals(Material.CROPS))
            {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SEEDS, 1) });
                b.setType(Material.AIR);
                e.setCancelled(true);
            }
        }
    }

    public static void growMe(Block b)
    {
        if (b.getData() != 7)
        {
            Random r = new Random();

            int num = r.nextInt(3) + 1;
            if (b.getData() + num < 8) {
                b.setData((byte)(b.getData() + num));
            } else {
                b.setData((byte)(b.getData() + 1));
            }
            if (num == 1) {
                b.getLocation().getWorld().playEffect(b.getLocation().add(0.5D, 0.5D, 0.5D), Effect.COLOURED_DUST, 2);
            }
            if (num == 2) {
                b.getLocation().getWorld().playEffect(b.getLocation().add(0.5D, 0.5D, 0.5D), Effect.CRIT, 3);
            }
            if (num == 3) {
                b.getLocation().getWorld().playEffect(b.getLocation().add(0.5D, 0.5D, 0.5D), Effect.INSTANT_SPELL, 3);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        if ((e.getBlock().getWorld().getName().toLowerCase().equals("f")) &&
                (e.getPlayer() != null) &&
                (!e.getPlayer().isOp()) &&
                (e.getBlock().getType() != Material.CROPS) && (e.getBlock().getType() != Material.CARROT) && (e.getBlock().getType() != Material.POTATO)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        if ((e.getBlock().getWorld().getName().toLowerCase().equals("f")) &&
                (e.getPlayer() != null)) {
            if ((e.getBlock().getType() == Material.CROPS) || (e.getBlock().getType() == Material.CARROT) || (e.getBlock().getType() == Material.POTATO))
            {
                if (e.getPlayer().getItemInHand().getItemMeta().equals(inventoryManager.magicHoe().getItemMeta())) {
                    breakMe(e.getBlock(), e.getPlayer(), e);
                }
            }
            else if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e)
    {
        if (e.getBlock().getWorld().getName().toLowerCase().equals("f"))
        {
            int id = e.getBlock().getTypeId();
            if ((id == 8) || (id == 9) || (id == 11)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void noTramplePls(PlayerInteractEvent event)
    {
        if (event.getPlayer().getWorld().getName().toString().toLowerCase().equals("f"))
        {
            if ((event.getAction() == Action.PHYSICAL) && (event.getClickedBlock().getType() == Material.SOIL)) {
                event.setCancelled(true);
            }
            if ((event.getPlayer().getItemInHand().getType().equals(Material.WATER_BUCKET)) || (event.getPlayer().getItemInHand().getType().equals(Material.LAVA_BUCKET))) {
                event.setCancelled(true);
            }
        }
    }
}

