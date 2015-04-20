package jeckelarmormod.content.items;

import jeckelarmormod.core.Refs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemModArmor extends ItemArmor
{
	public ItemModArmor(final String armorName, final ArmorMaterial material, final String textureName, final int armorType, final ItemStack repairStack)
	{
		super(material, 0, armorType);
		this.repairStack = repairStack;
		this.textureName = textureName;
		this.setUnlocalizedName(armorName);
		this.setTextureName(Refs.ModId + ":" + armorName);
	}

	public final String textureName;

	public final ItemStack repairStack;

	@Override public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return Refs.ModId + ":textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}

	public boolean getIsRepairable(ItemStack stackA, ItemStack stackB)
	{
		return OreDictionary.itemMatches(this.repairStack, stackB, true);
	}
}
