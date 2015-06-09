package jeckelarmormod.core;

import jeckelarmormod.common.configs.ConfigHandlerValues;
import net.minecraftforge.common.config.Configuration;

public class ConfigValues extends ConfigHandlerValues
{
	private static final long serialVersionUID = -7525760802697798721L;

	public ConfigValues()
	{
		this.add(this._updateChecking);
		this.add(this._villagerTrades);
		this.add(this._woodArmorEncumbrance);
		this.add(this._rockArmorEncumbrance);
	}

	public boolean isUpdateCheckingEnabled() { return this._updateChecking.getValue(); }
	protected final ConfigValueBoolean _updateChecking = new ConfigValueBoolean("EnableUpdateChecking", Configuration.CATEGORY_GENERAL,
			"Control automatic update checking.\n.Setting this option to false will disable version checking.",
			true);

	public boolean isVillagerTradesEnabled() { return this._villagerTrades.getValue(); }
	protected final ConfigValueBoolean _villagerTrades = new ConfigValueBoolean("EnableVillagerTrades", Configuration.CATEGORY_GENERAL,
			"Allow the mod armors to be included in villager trades.",
			true);

	public boolean isWoodArmorEncumbranceEnabled() { return this._woodArmorEncumbrance.getValue(); }
	protected final ConfigValueBoolean _woodArmorEncumbrance = new ConfigValueBoolean("ArmorEncumbranceWoodEnable", Configuration.CATEGORY_GENERAL,
			"When enabled, movement speed will be decreased while wearing wood armor.",
			false);

	public boolean isRockArmorEncumbranceEnabled() { return this._rockArmorEncumbrance.getValue(); }
	protected final ConfigValueBoolean _rockArmorEncumbrance = new ConfigValueBoolean("ArmorEncumbranceRockEnable", Configuration.CATEGORY_GENERAL,
			"When enabled, movement speed will be decreased while wearing rock armor.",
			false);

	public float getArmorEncumbranceValue() { return this._armorEncumbranceValue.getValue(); }
	protected final ConfigValueFloat _armorEncumbranceValue = new ConfigValueFloat("ArmorEncumbranceValue", Configuration.CATEGORY_GENERAL,
			"This value sets the percentage that each level of the Armor Encumbrance enchantment reduces player movement speed by.", 0.05f, 0.0f, 1.0f);

	@Override public void update(final Configuration config)
	{
		super.update(config);

		Refs.getUpdateChecker().enable(this.isUpdateCheckingEnabled());
	}
}
