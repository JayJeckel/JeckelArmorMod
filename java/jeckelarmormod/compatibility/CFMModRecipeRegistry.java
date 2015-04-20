package jeckelarmormod.compatibility;

import net.minecraft.item.ItemStack;
import jeckelarmormod.content.ContentData;
import jeckelarmormod.content.ContentManager;
import jeckelarmormod.core.Refs;

import com.mrcrayfish.furniture.api.IRecipeRegistry;
import com.mrcrayfish.furniture.api.RecipeVariables;

import cpw.mods.fml.common.event.FMLInterModComms;

public class CFMModRecipeRegistry
{
	public static void send()
	{
		FMLInterModComms.sendMessage("cfm", "register",
		Refs.ModId + ".compatibility.CFMModRecipeRegistry.initialize");
	}

	public static void initialize(IRecipeRegistry registry)
	{
		for (ContentData data : ContentManager.armor_wood) { register(registry, data); }

		for (ContentData data : ContentManager.armor_rock) { register(registry, data); }
	}

	private static void register(IRecipeRegistry registry, ContentData data)
	{
		RecipeVariables var;

		var = new RecipeVariables();
		var.addValue("input", new ItemStack(data.head));
		registry.registerRecipe("washingmachine", var);

		var = new RecipeVariables();
		var.addValue("input", new ItemStack(data.chest));
		registry.registerRecipe("washingmachine", var);

		var = new RecipeVariables();
		var.addValue("input", new ItemStack(data.leg));
		registry.registerRecipe("washingmachine", var);

		var = new RecipeVariables();
		var.addValue("input", new ItemStack(data.foot));
		registry.registerRecipe("washingmachine", var);
	}
}
