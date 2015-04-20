package jeckelarmormod.content;

import jeckelarmormod.content.items.ItemModArmor;
import jeckelarmormod.core.Refs;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ContentData
{
	public static ContentData create(final String armorName, final ArmorMaterial material, final ItemStack repairStack)
	{
		final Item head = new ItemModArmor(armorName + "_head", material, armorName, 0, repairStack);
		final Item chest = new ItemModArmor(armorName + "_chest", material, armorName, 1, repairStack);
		final Item leg = new ItemModArmor(armorName + "_leg", material, armorName, 2, repairStack);
		final Item foot = new ItemModArmor(armorName + "_foot", material, armorName, 3, repairStack);

		GameRegistry.registerItem(head, head.getUnlocalizedName());
		GameRegistry.registerItem(chest, chest.getUnlocalizedName());
		GameRegistry.registerItem(leg, leg.getUnlocalizedName());
		GameRegistry.registerItem(foot, foot.getUnlocalizedName());

		Refs.getTab().addItem(Refs.ModId, head);
		Refs.getTab().addItem(Refs.ModId, chest);
		Refs.getTab().addItem(Refs.ModId, leg);
		Refs.getTab().addItem(Refs.ModId, foot);

		return new ContentData(head, chest, leg, foot);
	}

	public ContentData(Item head, Item chest, Item leg, Item foot)
	{
		this.head = head;
		this.chest = chest;
		this.leg = leg;
		this.foot = foot;
	}

	public final Item head;
	public final Item chest;
	public final Item leg;
	public final Item foot;

	public void registerRecipes(final ItemStack input)
	{
		final IRecipe recipeHead = new ShapedOreRecipe(new ItemStack(this.head), "   ", "???", "? ?", '?', input);
		final IRecipe recipeChest = new ShapedOreRecipe(new ItemStack(this.chest), "? ?", "???", "???", '?', input);
		final IRecipe recipeLeg = new ShapedOreRecipe(new ItemStack(this.leg), "???", "? ?", "? ?", '?', input);
		final IRecipe recipeFoot = new ShapedOreRecipe(new ItemStack(this.foot), "   ", "? ?", "? ?", '?', input);

		GameRegistry.addRecipe(recipeHead);
		GameRegistry.addRecipe(recipeChest);
		GameRegistry.addRecipe(recipeLeg);
		GameRegistry.addRecipe(recipeFoot);
	}
}
