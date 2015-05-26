package jeckelarmormod.common;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CustomPotion extends Potion
{
	public CustomPotion(final int id, final String name, final boolean debuff, final int color)
	{
		super(id, debuff, color);
		this.setPotionName(name);
	}

	private ResourceLocation _texture = null;

	public CustomPotion setResourceLocation(final String modId, final String textureName)
	{
		this._texture = new ResourceLocation(modId, "textures/potions/" + textureName + ".png");
		return this;
	}

	@Override public boolean isReady(final int duration, final int amplifier) { return duration >= 1; }

	@Override public CustomPotion setIconIndex(final int col, final int row)
	{
		super.setIconIndex(col, row);
		return this;
	}

	@SideOnly(Side.CLIENT)
	@Override public int getStatusIconIndex()
	{
		if (this._texture != null) { Minecraft.getMinecraft().renderEngine.bindTexture(this._texture); }
		return super.getStatusIconIndex();
	}
}
