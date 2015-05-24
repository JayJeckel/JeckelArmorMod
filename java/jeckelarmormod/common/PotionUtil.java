package jeckelarmormod.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;

public final class PotionUtil
{
	private PotionUtil() { }

	public static int potionOffset;
	//private static final int MAXNEWPOTIONS = 1;

	public static int getNextID() { return potionOffset++ - 1; }

	public static void extendPotionsArray(final int count)
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Extending Potions Array.");
		potionOffset = Potion.potionTypes.length;

		Potion[] potionTypes = new Potion[potionOffset + count];
		System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, potionOffset);

		setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes", "field_76425_a");
	}

	public static <T, E> void setPrivateFinalValue(Class <? super T> classToAccess, T instance, E value, String... fieldNames)
	{
		Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));

		try
		{
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(instance, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
