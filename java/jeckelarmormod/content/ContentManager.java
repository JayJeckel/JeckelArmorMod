package jeckelarmormod.content;

import java.util.Random;

import jeckelarmormod.common.CustomPotion;
import jeckelarmormod.common.PotionUtil;
import jeckelarmormod.core.Refs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class ContentManager
{
	public static Potion potionEncumbranceArmor;

	public static final ArmorMaterial ArmorMaterialWood = EnumHelper.addArmorMaterial("WOOD",
			10, new int[] {1, 4, 3, 1}, 10);

	public static final ArmorMaterial ArmorMaterialRock = EnumHelper.addArmorMaterial("STONE",
			15, new int[] {2, 4, 4, 2}, 8);

	public static final String[] woods = new String[] { "oak", "spruce", "birch", "jungle", "acacia", "dark_oak" };
	public static final ContentData[] armor_wood = new ContentData[woods.length];

	public static final String[] rocks = new String[] { "stone" };
	public static final ContentData[] armor_rock = new ContentData[rocks.length];

	public void pre()
	{
		{
			PotionUtil.extendPotionsArray(1);

			final double duration = -Refs.getConfigValues().getArmorEncumbranceValue();//-0.05000000596046448D;
			potionEncumbranceArmor = new CustomPotion(PotionUtil.getNextID(), "potion.encumbrance.armor", true, 0).setResourceLocation(Refs.ModId, "potions").setIconIndex(0, 0);
			potionEncumbranceArmor.func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", duration, 2);
		}

		for (int index = 0; index < woods.length; index++)
		{ armor_wood[index] = ContentData.create("armor_planks_" + woods[index], ArmorMaterialWood, new ItemStack(Blocks.planks, 1, index)); }

		for (int index = 0; index < rocks.length; index++)
		{ armor_rock[index] = ContentData.create("armor_rock_" + rocks[index], ArmorMaterialRock, new ItemStack(Blocks.stone)); }

		Refs.getTab().setIconItemStack(new ItemStack(armor_wood[0].head));
	}

	public void initialize()
	{
		for (int index = 0; index < woods.length; index++)
		{ armor_wood[index].registerRecipes(new ItemStack(Blocks.planks, 1, index)); }

		for (int index = 0; index < rocks.length; index++)
		{ armor_rock[index].registerRecipes(new ItemStack(Blocks.stone)); }

		GameRegistry.registerFuelHandler(new ContentManager.FuelHandler());

		if (Refs.getConfigValues().isVillagerTradesEnabled())
		{
			for (EnumVillagers profession : EnumVillagers.values())
			{
				VillagerRegistry.instance().registerVillageTradeHandler(profession.ordinal(), new TradeHandler());
			}
		}
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

	public enum EnumVillagers
	{
		Farmer,
		Librarian,
		Priest,
		Blacksmith,
		Butcher
	}

	public class TradeHandler implements IVillageTradeHandler
	{
		@SuppressWarnings("unchecked")
		@Override public void manipulateTradesForVillager(final EntityVillager villager, final MerchantRecipeList list, final Random random)
		{
			final int count = woods.length + rocks.length;
			final int rand = random.nextInt(count);

			final ContentData data = (rand < woods.length ? armor_wood[rand] : armor_rock[rand - count]);

			final int type = random.nextInt(4);
			switch (type)
			{
				case 0: { list.add(new MerchantRecipe(new ItemStack(Items.gold_nugget, 1), new ItemStack(data.head))); }
				case 1: { list.add(new MerchantRecipe(new ItemStack(Items.gold_nugget, 4), new ItemStack(data.chest))); }
				case 2: { list.add(new MerchantRecipe(new ItemStack(Items.gold_nugget, 3), new ItemStack(data.leg))); }
				case 3: { list.add(new MerchantRecipe(new ItemStack(Items.gold_nugget, 2), new ItemStack(data.foot))); }
			}
		}
	}
}
