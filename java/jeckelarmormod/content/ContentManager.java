package jeckelarmormod.content;

import jeckelarmormod.core.Refs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class ContentManager
{
	public static final ArmorMaterial ArmorMaterialWood = EnumHelper.addArmorMaterial("WOOD",
			10, new int[] {1, 4, 3, 1}, 10);

	public static final ArmorMaterial ArmorMaterialStone = EnumHelper.addArmorMaterial("STONE",
			15, new int[] {2, 4, 4, 2}, 8);

	public static final String[] woods = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "dark_oak" };
	public static final ContentData[] armor_wood = new ContentData[woods.length];

	public static final String[] rocks = new String[] { "stone" };
	public static final ContentData[] armor_rock = new ContentData[rocks.length];

	public void pre()
	{
		for (int index = 0; index < woods.length; index++)
		{ armor_wood[index] = ContentData.create("armor_planks_" + woods[index], ArmorMaterialWood, new ItemStack(Blocks.planks, 1, index)); }

		for (int index = 0; index < rocks.length; index++)
		{ armor_rock[index] = ContentData.create("armor_rock_" + rocks[index], ArmorMaterialStone, new ItemStack(Blocks.stone)); }

		Refs.getTab().setIconItemStack(new ItemStack(armor_wood[0].head));
	}

	public void initialize()
	{
		for (int index = 0; index < woods.length; index++)
		{ armor_wood[index].registerRecipes(new ItemStack(Blocks.planks, 1, index)); }

		for (int index = 0; index < rocks.length; index++)
		{ armor_rock[index].registerRecipes(new ItemStack(Blocks.stone)); }

		GameRegistry.registerFuelHandler(new ContentManager.FuelHandler());
	}

	public void post()
	{
	}

	public static class FuelHandler implements IFuelHandler
	{
		@Override public int getBurnTime(ItemStack fuel)
		{
			if (fuel.getItem() instanceof ItemArmor && ((ItemArmor)fuel.getItem()).getArmorMaterial() == ArmorMaterialWood)
			{ return 300; }
			return 0;
		}
	}
}
