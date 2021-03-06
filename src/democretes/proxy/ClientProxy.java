package democretes.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import democretes.block.altar.TileAltar;
import democretes.block.coils.TileMachtCoil;
import democretes.block.generators.TilePurityGenerator;
import democretes.block.generators.TileRunicGenerator;
import democretes.block.generators.TileSolarGenerator;
import democretes.block.generators.TileSpreader;
import democretes.block.generators.TileSubTerraGenerator;
import democretes.block.machines.TileRuneConstructor;
import democretes.lib.RenderIds;
import democretes.render.blocks.RenderBlockAltar;
import democretes.render.blocks.RenderBlockCoil;
import democretes.render.blocks.RenderBlockGenerator;
import democretes.render.fx.FXOrb;
import democretes.render.tile.RenderAltar;
import democretes.render.tile.RenderMachtCoil;
import democretes.render.tile.RenderPurityGenerator;
import democretes.render.tile.RenderRuneConstructor;
import democretes.render.tile.RenderRuneGenerator;
import democretes.render.tile.RenderSolarGenerator;
import democretes.render.tile.RenderSpreader;
import democretes.render.tile.RenderSubTerraGenerator;
import democretes.utils.handlers.KeyHandler;

public class ClientProxy extends CommonProxy{

	@Override
	public void initSounds() {

    }

	@Override
    public void initRenderers() {
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSubTerraGenerator.class, new RenderSubTerraGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSolarGenerator.class, new RenderSolarGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TilePurityGenerator.class, new RenderPurityGenerator());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileSpreader.class, new RenderSpreader());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileRunicGenerator.class, new RenderRuneGenerator());    	
    	RenderIds.idGENERATOR = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileMachtCoil.class, new RenderMachtCoil());
    	RenderIds.idCOIL = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, new RenderAltar());
    	RenderIds.idALTAR = RenderingRegistry.getNextAvailableRenderId();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileRuneConstructor.class, new RenderRuneConstructor());
    	
		RenderingRegistry.registerBlockHandler(new RenderBlockGenerator());
		RenderingRegistry.registerBlockHandler(new RenderBlockCoil());
		RenderingRegistry.registerBlockHandler(new RenderBlockAltar());

    }
    
	@Override
	public void registerKeyBindings() {
		keyHandler = new KeyHandler();
		FMLCommonHandler.instance().bus().register(keyHandler);
	}
	
	private boolean doParticle() {
		float chance = 1F;
		if(Minecraft.getMinecraft().gameSettings.particleSetting == 1) {
			chance = 0.6F;
		}else if(Minecraft.getMinecraft().gameSettings.particleSetting == 2) {
			chance = 0.2F;
		}
		return Math.random() < chance;
	}
	
	@Override
	public void orbFX(World world, double x, double y, double z, float r, float g, float b, float size, int maxAge, boolean shrink) {
		if(!doParticle()) {
			return;
		}
		FXOrb sparkle = new FXOrb(world, x, y, z, 0.0D, 0.0D, 0.0D, r, g, b, size, maxAge, shrink);
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}
	
	@Override
	public void orbFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float r, float g, float b, float size, int maxAge, boolean shrink) {
		if(!doParticle()) {
			return;
		}
		FXOrb sparkle = new FXOrb(world, x, y, z, motionX, motionY, motionZ, r, g, b, size, maxAge, shrink);
		Minecraft.getMinecraft().effectRenderer.addEffect(sparkle);
	}
}
