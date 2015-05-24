package jeckelarmormod.common;

import net.minecraft.potion.Potion;

public class CustomPotion extends Potion
{
	public CustomPotion(final int id, final String name, final boolean debuff, final int color)
	{
		super(id, debuff, color);
		this.setPotionName(name);
		this.setIconIndex(1, 0);
	}

	@Override public boolean isReady(final int duration, final int amplifier)
	{
		return duration >= 1;
	}

	/*@SideOnly(Side.CLIENT)
	@Override public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("<modid>:textures/potions/<filename>.png"));
		return 1;
	}*/
}
