package jeckelarmormod.core;

import jeckelarmormod.common.configs.ConfigHandlerValues;
import net.minecraftforge.common.config.Configuration;

public class ConfigValues extends ConfigHandlerValues
{
	private static final long serialVersionUID = -7525760802697798721L;

	public ConfigValues()
	{
		this.add(this._updateChecking);
	}

	public boolean isUpdateCheckingEnabled() { return this._updateChecking.getValue(); }
	protected final ConfigValueBoolean _updateChecking = new ConfigValueBoolean("EnableUpdateChecking", Configuration.CATEGORY_GENERAL,
			"Control automatic update checking.\n.Setting this option to false will disable version checking.",
			true);

	public boolean isVillagerTradesEnabled() { return this._villagerTrades.getValue(); }
	protected final ConfigValueBoolean _villagerTrades = new ConfigValueBoolean("EnableVillagerTrades", Configuration.CATEGORY_GENERAL,
			"Allow the mod armors to be included in villager trades.",
			true);

	@Override public void update(final Configuration config)
	{
		super.update(config);

		Refs.getUpdateChecker().enable(this.isUpdateCheckingEnabled());
	}
}
