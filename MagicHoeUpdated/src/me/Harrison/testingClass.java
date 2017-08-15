package me.Harrison;

import me.clip.ezprestige.EZPrestige;
import me.clip.ezprestige.PrestigeManager;
import me.clip.ezprestige.objects.Prestige;
import net.buycraft.plugin.bukkit.BuycraftCommand;
import net.buycraft.plugin.bukkit.BuycraftListener;
import net.buycraft.plugin.shared.config.BuycraftConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class testingClass implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        Player p = (Player) s;
        if(cmd.getName().equalsIgnoreCase("test")) {
                if(PrestigeManager.isLastPrestige(p.getPlayer())) {

                    p.sendMessage("Last prestige");
                    if(PrestigeManager.getCurrentPrestigeTag(p).equalsIgnoreCase("10")) {
                        p.sendMessage("test");
                    } else {
                        p.sendMessage(PrestigeManager.getCurrentPrestigeTag(p));
                    }
                } else {
                    p.sendMessage("" + PrestigeManager.getCurrentPrestige(p.getPlayer()));
            }
        }
        return false;
    }
}
