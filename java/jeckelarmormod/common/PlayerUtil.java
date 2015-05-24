package jeckelarmormod.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

/**
 * Static helper class to centralize player related methods.
 * Copyright (c) 2015 Vinland Solutions
 * Creative Commons Attribution-NonCommercial <http://creativecommons.org/licenses/by-nc/3.0/deed.en_US>
 * @author JayJeckel <http://minecraft.jeckelland.site88.net/>
 */
public final class PlayerUtil
{
	/**
	 * This is a "static" class and should not be instanced.
	 */
	private PlayerUtil() { }

	public static boolean hasArmor(final EntityPlayer player)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			if (getArmor(player, rowIndex) != null) { return true; }
		}
		return false;
	}

	public static boolean hasArmor(final EntityPlayer player, final int rowIndex)
	{
		return (getArmor(player, rowIndex) != null);
	}

	public static ItemStack getArmor(final EntityPlayer player, final int rowIndex)
	{
		return player.inventory.getStackInSlot(36 + 3 - rowIndex);
	}

	public static void setArmor(final EntityPlayer player, final int rowIndex, final ItemStack stack)
	{
		player.inventory.setInventorySlotContents(36 + 3 - rowIndex, stack);
	}

	public static void addEffect(final EntityPlayer player, final int id, final int duration, final int amplifier, final boolean ambient)
	{
		player.addPotionEffect(new PotionEffect(id, duration, amplifier, ambient));
	}
}
