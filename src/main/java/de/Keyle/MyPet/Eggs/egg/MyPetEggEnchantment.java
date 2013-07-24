package de.Keyle.MyPet.Eggs.egg;

import de.Keyle.MyPet.util.logger.DebugLogger;
import de.Keyle.MyPet.util.logger.MyPetLogger;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MyPetEggEnchantment extends Enchantment
{
    public static Enchantment MYPET_EGG = new MyPetEggEnchantment(1054);

    private MyPetEggEnchantment(int id)
    {
        super(id);
    }

    @Override
    public String getName()
    {
        return "MyPetEggEnchantment";
    }

    @Override
    public int getMaxLevel()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getStartLevel()
    {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget()
    {
        return null;
    }

    @Override
    public boolean conflictsWith(Enchantment other)
    {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item)
    {
        return item.getType() == Material.MONSTER_EGG;
    }

    public static boolean registerEnchantment()
    {
        try
        {
            Field byIdField = Enchantment.class.getDeclaredField("byId");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byIdField.setAccessible(true);
            byNameField.setAccessible(true);

            @SuppressWarnings("unchecked")
            HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);

            if (byId.containsKey(MYPET_EGG.getId()))
            {
                if (byId.get(MYPET_EGG.getId()).getName().equals(MYPET_EGG.getName()))
                {
                    byId.remove(MYPET_EGG.getId());
                }
            }

            if (byName.containsKey(MYPET_EGG.getName()))
            {
                byName.remove(MYPET_EGG.getName());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);

            Enchantment.registerEnchantment(MYPET_EGG);
            DebugLogger.info("Registered Enchantment ID (" + MYPET_EGG.getId() + ") successfully.", "MyPet-Eggs");

        }
        catch (IllegalArgumentException e)
        {
            MyPetLogger.write("Can't register enchantment because Enchantment-ID is already taken. Please change the Enchantment-ID in the config!", "MyPet-Eggs");
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }
}