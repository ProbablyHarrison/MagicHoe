package me.Harrison;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class main extends JavaPlugin implements Listener {

        public final Logger logger = Logger.getLogger("Minecraft");
        public static Plugin plugin;

        public void onDisable()
        {
            PluginDescriptionFile pdfFile = getDescription();
            this.logger.info(pdfFile.getName() + " Disabled");

            plugin = null;
        }

        public void onEnable()
        {
            PluginDescriptionFile pdfFile = getDescription();
            this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Enabled");

            getServer().getPluginManager().registerEvents(new listeners(this), this);
            getServer().getPluginManager().registerEvents(new inventoryManager(), this);

            getCommand("test").setExecutor(new testingClass());

            getCommand(vars.mainCommand).setExecutor(new listeners(this));

            plugin = this;
        }

        public static Plugin getPlugin()
        {
            return plugin;
        }

        public static void registerEvents(Plugin plugin, Listener... listeners)
        {
            Listener[] arrayOfListener;
            int j = (arrayOfListener = listeners).length;
            for (int i = 0; i < j; i++)
            {
                Listener listener = arrayOfListener[i];
                Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
            }
        }
}
