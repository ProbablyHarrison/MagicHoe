package me.Harrison;

import org.bukkit.ChatColor;

public class vars {

    public static String mainCommand = "magictools";

    public static String c(String chat)
    {
        String message = ChatColor.translateAlternateColorCodes('&', chat);
        return message;
    }

    public static String magicHoeName = c("&a&lM&c&lA&e&lG&b&lI&3&lC&5&lH&6&lO&e&lE");
    public static String magicHammerName = c("&a&lM&c&lA&e&lG&b&lI&3&lC&5&lH&6&lA&e&lM&a&lM&9&lE&b&lR");

}
