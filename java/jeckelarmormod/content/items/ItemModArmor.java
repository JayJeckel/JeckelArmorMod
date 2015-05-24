package jeckelarmormod.content.items;

import jeckelarmormod.common.PlayerUtil;
import jeckelarmormod.content.ContentManager;
import jeckelarmormod.core.Refs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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

	@Override public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		if (ContentManager.potionEncumbranceArmor == null) { return; }
		if (Refs.getConfigValues().isWoodArmorEncumbranceEnabled() || Refs.getConfigValues().isRockArmorEncumbranceEnabled())
		{
			final boolean ambient = false;
			final int duration = 5;
			int total = 0;
			int amplifier = -1;
			if (Refs.getConfigValues().isWoodArmorEncumbranceEnabled())
			{
				final int count = getArmorCount(player, ContentManager.ArmorMaterialWood);
				total += count;
				if (count > 1) { amplifier += count - 1; }
			}
			if (Refs.getConfigValues().isRockArmorEncumbranceEnabled())
			{
				final int count = getArmorCount(player, ContentManager.ArmorMaterialRock);
				total += count;
				amplifier += count;
			}

			if (total > 0 && amplifier > -1)
			{
				PlayerUtil.addEffect(player, ContentManager.potionEncumbranceArmor.id, duration, amplifier, ambient);
			}
		}
	}

	public static int getArmorCount(final EntityPlayer player, final ArmorMaterial material)
	{
		int count = 0;
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			final ItemStack stack = PlayerUtil.getArmor(player, rowIndex);
			if (stack == null) { continue; }
			else if (!(stack.getItem() instanceof ItemArmor)) { continue; }
			else if (((ItemArmor)stack.getItem()).getArmorMaterial() != material) { continue; }
			else { count += 1; }
		}
		return count;
	}
}
