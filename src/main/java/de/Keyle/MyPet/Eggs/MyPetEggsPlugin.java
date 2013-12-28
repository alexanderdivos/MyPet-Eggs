package de.Keyle.MyPet.Eggs;

import de.Keyle.MyPet.Eggs.egg.MyPetEggEnchantment;
import de.Keyle.MyPet.Eggs.util.MyPetEggsVersion;
import de.Keyle.MyPet.util.MyPetVersion;
import de.Keyle.MyPet.util.logger.DebugLogger;
import de.Keyle.MyPet.util.logger.MyPetLogger;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;

public class MyPetEggsPlugin extends JavaPlugin implements Listener
{
    private static MyPetEggsPlugin plugin;

    public void onEnable()
    {
        plugin = this;

        DebugLogger.info("----------- loading MyPet-Eggs ... -----------", "MyPet-Eggs");

        if (!getServer().getPluginManager().isPluginEnabled("MyPet"))
        {
            MyPetLogger.write(ChatColor.RED + "MyPet plugin isn't enabled. Disable MyPet-Eggs.", "MyPet-Eggs");
            this.setEnabled(false);
            return;
        }

        if (Integer.parseInt(MyPetVersion.getBuild()) < Integer.parseInt(MyPetEggsVersion.getRequiredMyPetBuild())) {
            MyPetLogger.write(ChatColor.RED + "This version of MyPet-Eggs requires MyPet build-#" + MyPetEggsVersion.getRequiredMyPetBuild() + " or higher", "MyPet-Eggs");
            this.setEnabled(false);
            return;
        }

        try
        {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
            DebugLogger.info("MetricsLite activated", "MyPet-Eggs");
        }
        catch (IOException e)
        {
            DebugLogger.info("MetricsLite not activated", "MyPet-Eggs");
            DebugLogger.info(e.getMessage(), "MyPet-Eggs");
        }

        MyPetEggEnchantment.registerEnchantment();

        MyPetLogger.write("version " + MyPetEggsVersion.getMyPetEggsVersion() + "-b" + MyPetEggsVersion.getMyPetEggsBuild() + ChatColor.GREEN + " ENABLED", "MyPet-Eggs");
        DebugLogger.info("----------- MyPet-Eggs ready -----------", "MyPet-Eggs");
    }

    public static MyPetEggsPlugin getPlugin()
    {
        return plugin;
    }
}