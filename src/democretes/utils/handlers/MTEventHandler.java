package democretes.utils.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import democretes.api.AltarHelper;
import democretes.item.ItemsMT;
import democretes.lib.Reference;

public class MTEventHandler {
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
	    if(event.modID.equals(Reference.MOD_ID)) {
	        ConfigHandler.sync();
	    }
	}
	/*
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
		if(event.crafting != null) {
		
		}
	}
	*/
	
}
